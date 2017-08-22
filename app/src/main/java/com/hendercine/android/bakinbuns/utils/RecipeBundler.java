/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/7/17 12:33 PM
 */

package com.hendercine.android.bakinbuns.utils;

import android.os.Bundle;

import com.hendercine.android.bakinbuns.data.models.Recipe;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * BakinBuns created by hendercine on 8/7/17.
 */

public class RecipeBundler implements Bundler<Recipe> {

    @Override
    public void put(String s, Recipe recipe, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(recipe));
    }

    @Override
    public Recipe get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
