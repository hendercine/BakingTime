/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/29/17 12:55 PM
 */

package com.hendercine.android.bakinbuns.data.bundlers;

import android.os.Bundle;

import com.hendercine.android.bakinbuns.data.models.Recipe;

import org.parceler.Parcels;

import java.util.List;

import icepick.Bundler;

/**
 * BakinBuns created by hendercine on 8/29/17.
 */

public class RecipeListBundler implements Bundler<List<Recipe>> {

    @Override
    public void put(String s, List<Recipe> recipes, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(recipes));
    }

    @Override
    public List<Recipe> get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
