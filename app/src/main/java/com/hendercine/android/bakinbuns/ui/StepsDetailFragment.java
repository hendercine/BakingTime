package com.hendercine.android.bakinbuns.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.StepsRecyclerViewAdapter;
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

public class StepsDetailFragment extends Fragment implements
        StepsRecyclerViewAdapter.ItemClickListener{

    @State boolean mIsDualPane;
    private Recipe mRecipe = new Recipe();

    public StepsDetailFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View stepsListView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        return stepsListView;
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
