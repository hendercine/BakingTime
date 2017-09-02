/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/31/17 10:15 PM
 */

package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Recipe;

import java.util.List;

import butterknife.BindView;

/**
 * BakinBuns created by hendercine on 8/31/17.
 */

public class StepsListFragment extends Fragment {

    @Nullable
    @BindView(R.id.steps_list)
    RecyclerView stepsListView;

    private List<Recipe> mRecipeIds;
    private int mListIndex;

    public StepsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.fragment_steps_list, container, false);
//
//        ArrayList<Recipe> recipe = Parcels.unwrap(getIntent()
//                .getParcelableExtra("mRecipeArrayList"));
//
//        mRecipeIds =
//
        return rootView;
    }
//
//    public void setRecipeSteps(ArrayList<Step> recipeSteps) {
//        mRecipeIds = recipeSteps;
//    }
//
//    public void setListIndex(int index) {
//        mListIndex = index;
//    }
    }

