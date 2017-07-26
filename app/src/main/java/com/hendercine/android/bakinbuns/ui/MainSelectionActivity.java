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
import timber.log.Timber;

public class MainSelectionActivity extends AppCompatActivity implements
        MainRecyclerViewGridAdapter.ItemClickListener {

    private RefWatcher refWatcher;
    private MainRecyclerViewGridAdapter mAdapter;
    private RecipeService mBackAppApi;

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

        List<Recipe> recipes = new ArrayList<>();
        recipes.set(0, ).recipeName;

//        ArrayList<String> data = new ArrayList<>(Arrays.asList("Banana Bread",
//                "Serves 8", "Poop Pie", "Serves 4",
//                        "5", "6", "7",
//                        "8", "9",
//                        "10", "11",
//                        "12", "13", "14", "15", "16", "17", "18", "19", "20",
//                        "21", "22", "23", "24", "25", "26", "27", "28", "29",
//                        "30", "31", "32", "33", "34", "35", "36", "37", "38",
//                        "39", "40", "41", "42", "43", "44", "45", "46", "47",
//                        "48"));

        int spanCount;
        int spacingInPixels = 50;
        RecyclerView convertView;

        if (mIsDualPane) {
            convertView = dualPaneGridCards;
            spanCount = 3;
        } else {
            convertView = singlePaneGridCards;
            spanCount = 1;
        }
        if (convertView != null) {
            convertView.setLayoutManager(new GridLayoutManager(this,
                    spanCount));
            mAdapter = new MainRecyclerViewGridAdapter(this, recipes);
            mAdapter.setClickListener(this);
            convertView.setAdapter(mAdapter);
            convertView.addItemDecoration(new GridSpacingItemDecoration(spanCount,
                    spacingInPixels, true));
        }
//        dualPaneGridCards.setBackgroundColor(getResources().getColor(R.color
//                .colorAccent));
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
