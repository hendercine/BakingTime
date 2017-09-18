/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 9/9/17 9:10 PM
 */

package com.hendercine.android.bakinbuns.data.bundlers;

import android.os.Bundle;

import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import java.util.ArrayList;

import icepick.Bundler;

/**
 * BakinBuns created by hendercine on 9/9/17.
 */

public class StepListBundler implements Bundler<ArrayList<Step>> {
    @Override
    public void put(String s, ArrayList<Step> steps, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(steps));
    }

    @Override
    public ArrayList<Step> get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
