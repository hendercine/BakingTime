package com.hendercine.android.bakinbuns.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Recipe;

import icepick.State;

//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.app.ActionBar;
//import android.view.MenuItem;

///**
// * A fragment representing a single Recipe detail screen.
// * This fragment is either contained in a {@link RecipeStepsActivity}
// * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
// * on handsets.
// */

public class StepsDetailFragment extends Fragment {

    //    /**
//     * The fragment argument representing the item ID that this fragment
//     * represents.
//     */
    public static final String ARG_ITEM_ID = "item_id";

    @State boolean mIsDualPane;
    private Recipe mRecipe = new Recipe();

    public StepsDetailFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            // Load the content specified by the fragment arguments.

//            mRecipe = DummyContent.ITEM_MAP.get(getArguments().getString
//                    (ARG_ITEM_ID));
//
            Activity activity = this.getActivity();

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View stepsListView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        return stepsListView;
    }

//    @Override
//    public void onItemClick(View view, int position) {
//
//    }
}
