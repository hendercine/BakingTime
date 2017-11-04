/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/23/17 1:35 PM
 */

package com.hendercine.android.bakingtime.data.bundlers;

import android.os.Bundle;

import com.hendercine.android.bakingtime.data.models.Ingredient;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * BakingTime created by hendercine on 8/29/17.
 */

public class IngredientBundler implements Bundler<Ingredient> {
    @Override
    public void put(String s, Ingredient ingredient, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(ingredient));
    }

    @Override
    public Ingredient get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
