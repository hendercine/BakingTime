/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/22/17 6:08 PM
 */

package com.hendercine.android.bakinbuns.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.MainRecyclerViewGridAdapter;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.network.RecipeService;
import com.hendercine.android.bakinbuns.utils.GridSpacingItemDecoration;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainSelectionActivity extends AppCompatActivity implements
        MainRecyclerViewGridAdapter.ItemClickListener {

    private RefWatcher refWatcher;
    private MainRecyclerViewGridAdapter mAdapter;
    List<Recipe> list;
    Recipe mRecipe;
    RecipeService service;
    private Subscription subscription;

    @Nullable
    @BindView(R.id.dual_pane_rv_recipe_cards)
    RecyclerView dualPaneGridCards;

    @Nullable
    @BindView(R.id.single_pane_rv_recipe_cards)
    RecyclerView singlePaneGridCards;
    boolean mIsDualPane;

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
        mIsDualPane = dualPaneGridCards != null &&
                dualPaneGridCards.getVisibility() == View.VISIBLE;

        getRecipeData();
    }

    public void getRecipeData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://go.udacity.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RecipeService recipeService = retrofit.create(RecipeService.class);

        Observable<List<Recipe>> observable = recipeService.getRecipeData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<Recipe>>() {
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
            public void onNext(List<Recipe> recipes) {
                Timber.d("In onNext()");
                list = new ArrayList<>();
                for (int i = 0; i < recipes.size(); i++) {

                    Recipe recipe = new Recipe();
                    recipe.setRecipeName(recipes.get(i).getRecipeName());
                    recipe.setServings(recipes.get(i).getServings());
                    list.add(recipe);
                }
                int spanCount;
                int spacingInPixels = 50;
                RecyclerView convertView;
                Timber.d("In onError()");
                if (mIsDualPane) {
                    convertView = dualPaneGridCards;
                    spanCount = 3;
                } else {
                    convertView = singlePaneGridCards;
                    spanCount = 1;
                }
                if (convertView != null) {
                    convertView.setLayoutManager(new GridLayoutManager
                            (MainSelectionActivity.this, spanCount));
                    mAdapter = new MainRecyclerViewGridAdapter(list);
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
        Timber.i("TAG", "You clicked number "
                + mAdapter.getItem(position)
                + ", "
                + "which is at cell position: "
                + position);
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
