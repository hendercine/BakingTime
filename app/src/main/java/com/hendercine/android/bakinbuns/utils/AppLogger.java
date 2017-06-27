/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 4:39 PM
 */

package com.hendercine.android.bakinbuns.utils;

import com.hendercine.android.bakinbuns.BuildConfig;

import timber.log.Timber;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public class AppLogger {

    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void d(String s, Object... objects) {
        Timber.d(s, objects);
    }

    public static void d(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void i(String s, Object... objects) {
        Timber.i(s, objects);
    }

    public static void i(Throwable throwable, String s, Object... objects) {
        Timber.i(throwable, s, objects);
    }

    public static void w(String s, Object... objects) {
        Timber.d(s, objects);
    }

    public static void w(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

    public static void e(String s, Object... objects) {
        Timber.d(s, objects);
    }

    public static void e(Throwable throwable, String s, Object... objects) {
        Timber.d(throwable, s, objects);
    }

}
