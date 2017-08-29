package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.hendercine.android.bakinbuns.R;

import butterknife.BindView;
import icepick.Icepick;

///**
// * An activity representing a list of Recipes. This activity
// * has different presentations for handset and tablet-size devices. On
// * handsets, the activity presents a list of items, which when touched,
// * lead to a {@link RecipeDetailActivity} representing
// * item details. On tablets, the activity presents the list of items and
// * item details side-by-side using two vertical panes.
// */
public class RecipeStepsActivity extends AppCompatActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mIsDualPane;

    @Nullable
    @BindView(R.id.recipe_detail_container)
    FrameLayout detailsContainer;

    @Nullable
    @BindView(R.id.steps_list)
    RecyclerView stepsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.steps_list);
        assert recyclerView != null;


        if (detailsContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mIsDualPane = true;
        }
    }

}
