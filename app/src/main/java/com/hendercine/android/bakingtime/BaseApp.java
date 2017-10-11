/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/21/17 5:52 PM
 */

package com.hendercine.android.bakingtime;

import android.app.Application;
import android.util.Log;

import timber.log.Timber;

/**
 * BakinBuns created by hendercine on 8/21/17.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            CrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    CrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    CrashLibrary.logWarning(t);
                }
            }

        }
    }
}
