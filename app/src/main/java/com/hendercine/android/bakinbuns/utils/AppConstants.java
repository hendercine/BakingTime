/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 4:21 PM
 */

package com.hendercine.android.bakinbuns.utils;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public final class AppConstants {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "bakin_buns.db";

    public static final long NULL_INDEX = -1L;

    public static final String RECIPE_JSON_URL = "http://go.udacity.com/android-baking-app-json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    private AppConstants() {
        // This utility is not public instantiable
    }
}
