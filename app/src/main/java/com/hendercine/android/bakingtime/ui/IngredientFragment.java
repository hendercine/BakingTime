/*
* Created by James Henderson on 2017
* Copyright (c) Hendercine Productions and James Henderson 2017.
* All rights reserved.
*
* Last modified 10/5/17 3:37 PM
*/

package com.hendercine.android.bakingtime.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.adapters.IngredientRVAdapter;
import com.hendercine.android.bakingtime.data.bundlers.IngredientListBundler;
import com.hendercine.android.bakingtime.data.bundlers.RecipeBundler;
import com.hendercine.android.bakingtime.data.models.Ingredient;
import com.hendercine.android.bakingtime.data.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment {

    private static final String TAG = IngredientFragment.class.getSimpleName();
    private static final String RECIPE = "ingredients";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.ingredient_list)
    RecyclerView mIngredientListView;

    @State(RecipeBundler.class)
    Recipe mRecipe;

    @State(IngredientListBundler.class)
    ArrayList<Ingredient> mIngredientList;
    private RemoveFragmentListener removeListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientFragment() {
    }

    public IngredientFragment newInstance(Recipe recipe) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelable(RECIPE, Parcels.wrap(recipe));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            removeListener = (RemoveFragmentListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(getActivity()
                    .getClass().getSimpleName()
                    + getString(R.string.remove_fragment_listener_error), e);
        }
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

        // Show the toolbar and send callback to parent activity.
        toolbar.setTitle(mRecipe.getRecipeName());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeListener.onRemoveFragment(TAG);
            }
        });

        // Set the adapter
        IngredientRVAdapter adapter = new IngredientRVAdapter(mIngredientList);
        if (mIngredientListView != null) {
            mIngredientListView.setLayoutManager(new LinearLayoutManager(getContext()));
            mIngredientListView.setAdapter(adapter);
        }
        return rootView;
    }

}
