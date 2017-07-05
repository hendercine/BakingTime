/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 10:45 AM
 */

package com.hendercine.android.bakinbuns.ui.steps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.db.model.Step;
import com.hendercine.android.bakinbuns.ui.base.BaseActivity;
import com.hendercine.android.bakinbuns.ui.details.DetailFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DetailFragment} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class StepsActivity extends BaseActivity implements StepsMvpView {

    @Inject
    StepsMvpPresenter<StepsMvpView> mPresenter;

    @Inject
    StepsAdapter mStepsAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    List<Step> mStepsList;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ingredient_title)
    TextView ingredientTitleView;
    @BindView(R.id.recipe_step_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.step_detail_container)
    View step_detail_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        setUp();

        if (step_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    protected void setUp() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());

        assert recyclerView != null;
        setupRecyclerView(recyclerView);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(mStepsAdapter);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void updateStep(List<Step> stepList) {

    }

}

