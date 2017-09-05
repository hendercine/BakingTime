/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/22/17 6:08 PM
 */

package com.hendercine.android.bakinbuns.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.MainRecyclerViewGridAdapter;
import com.hendercine.android.bakinbuns.data.bundlers.IngredientBundler;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeBundler;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeListBundler;
import com.hendercine.android.bakinbuns.data.bundlers.StepBundler;
import com.hendercine.android.bakinbuns.data.db.RecipeDbHandler;
import com.hendercine.android.bakinbuns.data.models.Ingredient;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;
import com.hendercine.android.bakinbuns.data.network.RecipeClient;
import com.hendercine.android.bakinbuns.utils.GridSpacingItemDecoration;
import com.hendercine.android.bakinbuns.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainSelectionActivity extends AppCompatActivity implements
        MainRecyclerViewGridAdapter.ItemClickListener {

    private RefWatcher refWatcher;
    private MainRecyclerViewGridAdapter mAdapter;
    private Intent intent;

    @State(RecipeListBundler.class)
    ArrayList<Recipe> recipeList;

    @State(RecipeBundler.class)
    Recipe mRecipe;
    @State(StepBundler.class)
    Step mStep;
    @State(IngredientBundler.class)
    Ingredient mIngredient;

    RecipeDbHandler dbHandler;
    Subscription subscription;
    Utils mUtils;

    @Nullable
    @BindView(R.id.tablet_rv_recipe_cards)
    RecyclerView tabletGridCards;

    @Nullable
    @BindView(R.id.hand_held_rv_recipe_cards)
    RecyclerView handHeldGridCards;

    @State
    boolean mIsTablet;

    // Create the LeakCanary watcher
    public static RefWatcher getRefWatcher(Context context) {
        MainSelectionActivity activity = (MainSelectionActivity) context.getApplicationContext();
        return activity.refWatcher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        // Install the LeakCanary watcher
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(getApplication());

        setContentView(R.layout.activity_main_selection);
        ButterKnife.bind(this);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");
        mIsTablet = tabletGridCards != null &&
                tabletGridCards.getVisibility() == View.VISIBLE;

        getRecipeData();
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
                        e.printStackTrace();
                        Timber.d("In onError()");
                    }

                    @Override
                    public void onNext(ArrayList<Recipe> recipes) {
                        Timber.d("In onNext()");
                        recipeList = new ArrayList<>();
                        for (int i = 0; i < recipes.size(); i++) {

                            mRecipe = new Recipe();

                            mRecipe.setRecipeName(recipes.get(i).getRecipeName());
                            mRecipe.setServings(recipes.get(i).getServings());
                            mRecipe.setStepList(recipes.get(i).getStepList());
                            mRecipe.setIngredientList(recipes.get(i).getIngredientList());

//                            Bundle bundle = new Bundle();
//                            bundle.putParcelable("recipe", Parcels.wrap(mRecipe));
                            intent = new Intent(
                                    MainSelectionActivity.this,
                                    RecipeStepsActivity.class);
                            intent.putExtra("recipe", Parcels.wrap(mRecipe));

                            recipeList.add(mRecipe);
                        }
                            int spanCount;
                            int spacingInPixels = 50;
                            RecyclerView convertView;

                            if (mIsTablet) {
                                convertView = tabletGridCards;
                                spanCount = 3;
                            } else {
                                convertView = handHeldGridCards;
                                spanCount = 1;
                            }
                            if (convertView != null) {
                                convertView.setLayoutManager(new GridLayoutManager
                                        (MainSelectionActivity.this, spanCount));
                                mAdapter = new MainRecyclerViewGridAdapter(recipeList);
                                mAdapter.setClickListener(MainSelectionActivity.this);
                                convertView.setAdapter(mAdapter);
                            }
                            assert convertView != null;
                            convertView.addItemDecoration(new GridSpacingItemDecoration(spanCount,
                                    spacingInPixels, true));
                    }

                });
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onItemClick(View view, int position) {
//        Timber.i("TAG", "You clicked number "
//                + mAdapter.getItem(position)
//                + ", "
//                + "which is at cell position: "
//                + position);
        startActivity(intent);
    }

    // TODO: Remove or uncomment and modify the following code.
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
