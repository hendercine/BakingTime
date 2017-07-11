/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:13 PM
 */

package com.hendercine.android.bakinbuns;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.JsonReader;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Utils {

    static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    static JsonReader readJSONData(Context context)
}
