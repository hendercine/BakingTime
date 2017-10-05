/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/22/17 6:08 PM
 */

package com.hendercine.android.bakinbuns.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.MainRecyclerViewGridAdapter;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeBundler;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeListBundler;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.network.RecipeClient;
import com.hendercine.android.bakinbuns.utils.GridSpacingItemDecoration;
import com.hendercine.android.bakinbuns.utils.Utils;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;
import io.andref.rx.network.RxNetwork;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MainSelectionActivity extends AppCompatActivity implements
        MainRecyclerViewGridAdapter.ItemClickListener {

    private MainRecyclerViewGridAdapter mAdapter;
    private Subscription subscription;
    private CompositeSubscription mCompositeSubscription;
    private ConnectivityManager mConnectivityManager;
    private RecyclerView convertView;
    private int spanCount;
    private Utils mUtils;

    @Nullable
    @BindView(R.id.tablet_rv_recipe_cards)
    RecyclerView tabletGridCards;

    @Nullable
    @BindView(R.id.hand_held_rv_recipe_cards)
    RecyclerView handHeldGridCards;

    @State
    boolean mIsTablet;

    @State(RecipeListBundler.class)
    ArrayList<Recipe> recipeList;

    @State(RecipeBundler.class)
    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        setContentView(R.layout.activity_main_selection);
        ButterKnife.bind(this);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
        mIsTablet = tabletGridCards != null &&
                tabletGridCards.getVisibility() == View.VISIBLE;

        if (mIsTablet) {
            convertView = tabletGridCards;
            spanCount = 3;
        } else {
            convertView = handHeldGridCards;
            spanCount = 1;
        }

        assert convertView != null;
        int spacingInPixels = 50;
        convertView.addItemDecoration(
                new GridSpacingItemDecoration(spanCount, spacingInPixels, true));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCompositeSubscription.unsubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Use CompositeSubscription to check if network is available.
        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(
                RxNetwork.connectivityChanges(this, mConnectivityManager)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean connected) {
                                if (connected) {
                                    getRecipeData();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            R.string.no_internet,
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onItemClick(View view, int position) {
        position = convertView.getChildLayoutPosition(view);
        Recipe recipeItem = recipeList.get(position);
        Intent intent = new Intent(
                MainSelectionActivity.this,
                StepsListActivity.class);
        intent.putExtra("recipe", Parcels.wrap(recipeItem));
        startActivity(intent);
    }

    public void getRecipeData() {

        subscription = RecipeClient.getInstance()
                .getRecipeFromJson()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Recipe>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("In onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("In onError()");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ArrayList<Recipe> recipes) {
                        Timber.d("In onNext()");
                        recipeList = new ArrayList<>();
                        for (int i = 0; i < recipes.size(); i++) {

                            mRecipe = new Recipe();

                            mRecipe.setRecipeId(recipes.get(i).getRecipeId());
                            mRecipe.setRecipeName(recipes.get(i).getRecipeName());
                            mRecipe.setServings(recipes.get(i).getServings());
                            mRecipe.setStepList(recipes.get(i).getStepList());
                            mRecipe.setIngredientList(recipes.get(i).getIngredientList());

                            recipeList.add(mRecipe);
                        }

                        if (convertView != null) {
                            convertView.setLayoutManager(new GridLayoutManager
                                    (MainSelectionActivity.this, spanCount));
                            mAdapter = new MainRecyclerViewGridAdapter(recipeList);
                            mAdapter.setClickListener(MainSelectionActivity.this);
                            convertView.setAdapter(mAdapter);
                        }
                    }
                });
    }
}
