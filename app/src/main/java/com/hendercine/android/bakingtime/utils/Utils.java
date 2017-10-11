/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/20/17 10:00 PM
 */

package com.hendercine.android.bakingtime.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Utils {

    public static final ButterKnife.Action<RecyclerView> DISABLE = new ButterKnife
            .Action<RecyclerView>() {
        @Override
        public void apply(RecyclerView view, int index) {
            view.setEnabled(false);
        }
    };

    public static final ButterKnife.Setter<RecyclerView, Boolean> ENABLED =
            new ButterKnife.Setter<RecyclerView, Boolean>() {
                @Override
                public void set(@NonNull RecyclerView view, Boolean value, int index) {
                    view.setEnabled(value);
                }
            };
}
