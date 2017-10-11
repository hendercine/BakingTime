/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/29/17 1:17 PM
 */

package com.hendercine.android.bakingtime.data.bundlers;

import android.os.Bundle;

import com.hendercine.android.bakingtime.data.models.Step;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * BakinBuns created by hendercine on 8/29/17.
 */

public class StepBundler implements Bundler<Step> {
    @Override
    public void put(String s, Step step, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(step));
    }

    @Override
    public Step get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
