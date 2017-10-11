/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/31/17 3:25 PM
 */

package com.hendercine.android.bakingtime.data.bundlers;

import android.os.Bundle;

import com.hendercine.android.bakingtime.ui.StepsDetailFragment;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * BakinBuns created by hendercine on 8/31/17.
 */

public class DetailFragmentBundler implements Bundler<StepsDetailFragment> {

    @Override
    public void put(String s, StepsDetailFragment stepsDetailFragment, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(stepsDetailFragment));
    }

    @Override
    public StepsDetailFragment get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
