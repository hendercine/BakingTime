/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/20/17 10:00 PM
 */

package com.hendercine.android.bakinbuns.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Utils {

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

//    static JsonReader readJSONData(Context context)
}
