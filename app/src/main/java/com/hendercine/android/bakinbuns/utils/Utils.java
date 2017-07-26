/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/20/17 10:00 PM
 */

package com.hendercine.android.bakinbuns.utils;

import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Utils {

    public static final ButterKnife.Action<RecyclerView> DISABLE = new ButterKnife
            .Action<RecyclerView>() {
        @Override public void apply(RecyclerView view, int index) {
            view.setEnabled(false);
        }
    };

    public static final ButterKnife.Setter<RecyclerView, Boolean> ENABLED =
            new ButterKnife.Setter<RecyclerView, Boolean>() {
        @Override public void set(RecyclerView view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

//    public static int calculateNoOfColumns(Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        int noOfColumns = (int) (dpWidth / 180);
//        return noOfColumns;
//    }

//    static JsonReader readJSONData(Context context)
}