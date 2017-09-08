package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Recipe;

//import android.support.design.widget.CollapsingToolbarLayout;
//import android.widget.TextView;
//
//

public class RecipeDetailFragment extends Fragment {

//
//    /**
//     * The content this fragment is presenting.
//     */
    private Recipe mRecipe;

//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
    public RecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
//
//        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(mItem.details);
//        }

        return rootView;
    }
}
