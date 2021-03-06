/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 11/1/17 4:48 PM
 */

package com.hendercine.android.bakingtime.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.adapters.MainRecyclerViewGridAdapter;
import com.hendercine.android.bakingtime.data.bundlers.RecipeBundler;
import com.hendercine.android.bakingtime.data.bundlers.RecipeListBundler;
import com.hendercine.android.bakingtime.data.models.Recipe;
import com.hendercine.android.bakingtime.data.network.RecipeClient;
import com.hendercine.android.bakingtime.utils.GridSpacingItemDecoration;
import com.hendercine.android.bakingtime.utils.SimpleIdlingResource;

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

    private static final String POSITION_STATE_KEY = "scroll_position";
    private MainRecyclerViewGridAdapter mAdapter;
    private Subscription subscription;
    private CompositeSubscription mCompositeSubscription;
    private ConnectivityManager mConnectivityManager;
    private RecyclerView convertView;
    private GridLayoutManager mGridLayoutManager;
    private ProgressListener mListener;
    private MenuItem mActionProgressItem;
    private int mScrollPosition;

    @State
    boolean mIsTablet;
    @State(RecipeListBundler.class)
    ArrayList<Recipe> recipeList;
    @State(RecipeBundler.class)
    Recipe mRecipe;

    @Nullable
    @BindView(R.id.tablet_rv_recipe_cards)
    RecyclerView tabletGridCards;
    @Nullable
    @BindView(R.id.hand_held_rv_recipe_cards)
    RecyclerView handHeldGridCards;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            long waitingTime = 5000;
            mIdlingResource = new SimpleIdlingResource(waitingTime);
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        setContentView(R.layout.activity_main_selection);
        ButterKnife.bind(this);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        mIsTablet = tabletGridCards != null &&
                tabletGridCards.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mScrollPosition = savedInstanceState.getInt(POSITION_STATE_KEY);
        } else {
            recipeList = new ArrayList<>();
            mScrollPosition = 0;
        }

        int spanCount;
        // Set the spanCount for the view depending on device type.
        if (mIsTablet) {
            convertView = tabletGridCards;
            spanCount = 3;
        } else {
            convertView = handHeldGridCards;
            spanCount = 1;
        }

        mGridLayoutManager = new GridLayoutManager
                (MainSelectionActivity.this, spanCount);

        assert convertView != null;
        int spacingInPixels = 50;

        convertView.setLayoutManager(mGridLayoutManager);

        mAdapter = new MainRecyclerViewGridAdapter(recipeList);
        mAdapter.setClickListener(MainSelectionActivity.this);
        convertView.setAdapter(mAdapter);

        convertView.addItemDecoration(
                new GridSpacingItemDecoration(spanCount, spacingInPixels, true));

        getRecipeData();
        getIdlingResource();
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
                                    Timber.i(getString(R.string.network_is_online));
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
    protected void onPause() {
        super.onPause();
        mCompositeSubscription.unsubscribe();
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
        mScrollPosition = mGridLayoutManager.findFirstCompletelyVisibleItemPosition();
        outState.putInt(POSITION_STATE_KEY, mScrollPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mActionProgressItem = menu.findItem(R.id.menu_action_progress);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onItemClick(View view, int position) {
        position = convertView.getChildLayoutPosition(view);
        Recipe recipeItem = recipeList.get(position);
        Intent intent = new Intent(
                MainSelectionActivity.this,
                StepsListActivity.class);
        intent.putExtra("recipe", Parcels.wrap(recipeItem));
        showProgressBar();
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
                        hideProgressBar();
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
                            mRecipe.setImageUrl(recipes.get(i).getImageUrl());
                            mRecipe.setStepList(recipes.get(i).getStepList());
                            mRecipe.setIngredientList(recipes.get(i).getIngredientList());

                            recipeList.add(mRecipe);
                        }
                        convertView.setLayoutManager(mGridLayoutManager);
                        convertView.smoothScrollToPosition(mScrollPosition);
                        mAdapter.setList(recipeList);
                    }
                });
    }

    public interface ProgressListener {
        void onProgressShown();

        void onProgressHidden();
    }

    private void showProgressBar() {
        // show the progress and notify the listener
        mActionProgressItem.setVisible(true);
        notifyListener(mListener);
    }

    private void hideProgressBar() {
        // hide the progress and notify the listener
        if (mActionProgressItem != null) {
            mActionProgressItem.setVisible(false);
        }
        notifyListener(mListener);
    }

    public boolean isInProgress() {
        // return true if progress is visible
        boolean progressVisible = false;
        if (mActionProgressItem != null) {
            if (mActionProgressItem.isVisible()) {
                progressVisible = true;
            }
        }
        return progressVisible;
    }

    private void notifyListener(ProgressListener listener) {
        if (listener == null) {
            return;
        }
        if (isInProgress()) {
            listener.onProgressShown();
        } else {
            listener.onProgressHidden();
        }
    }
}
