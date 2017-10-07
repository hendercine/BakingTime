/*
* Created by James Henderson on 2017
* Copyright (c) Hendercine Productions and James Henderson 2017.
* All rights reserved.
*
* Last modified 10/5/17 3:37 PM
*/

package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.IngredientRVAdapter;
import com.hendercine.android.bakinbuns.data.bundlers.IngredientListBundler;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeBundler;
import com.hendercine.android.bakinbuns.data.models.Ingredient;
import com.hendercine.android.bakinbuns.data.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment {

    private static final String RECIPE = "ingredients";

    @Nullable
    @BindView(R.id.ingredient_list)
    RecyclerView mIngredientListView;

    @State(RecipeBundler.class)
    Recipe mRecipe;

    @State(IngredientListBundler.class)
    ArrayList<Ingredient> mIngredientList;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientFragment() {
    }

    public static IngredientFragment newInstance(Recipe recipe) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelable(RECIPE, Parcels.wrap(recipe));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mRecipe = Parcels.unwrap(getArguments().getParcelable(RECIPE));
            mIngredientList = new ArrayList<>();
            mIngredientList = mRecipe.getIngredientList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_ingredient_list, container, false);
        ButterKnife.bind(this, rootView);

        // Set the adapter
        IngredientRVAdapter adapter =
                new IngredientRVAdapter(mIngredientList);
        mIngredientListView = (RecyclerView) rootView;
        mIngredientListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mIngredientListView.setAdapter(adapter);
        return rootView;
    }
}
