/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/23/17 1:35 PM
 */

package com.hendercine.android.bakingtime.data.bundlers;

import android.os.Bundle;

import com.hendercine.android.bakingtime.data.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;

import icepick.Bundler;

/**
 * BakingTime created by hendercine on 8/29/17.
 */

public class RecipeListBundler implements Bundler<ArrayList<Recipe>> {

    @Override
    public void put(String s, ArrayList<Recipe> recipes, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(recipes));
    }

    @Override
    public ArrayList<Recipe> get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
