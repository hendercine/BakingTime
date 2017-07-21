package com.hendercine.android.bakinbuns.network;

import android.os.AsyncTask;

import com.hendercine.android.bakinbuns.MainSelectionActivity;
import com.hendercine.android.bakinbuns.objects.Recipe;
import com.hendercine.android.bakinbuns.objects.RecipeAdapter;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 6/19/17.
 */

public class FetchRecipesTask extends AsyncTask<String, Void, ArrayList<Recipe>> {

    private MainSelectionActivity mainSelectionActivity;
    private final String TAG = FetchRecipesTask.class.getSimpleName();
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected ArrayList<Recipe> doInBackground(String... params) {
        return null;
    }
}
