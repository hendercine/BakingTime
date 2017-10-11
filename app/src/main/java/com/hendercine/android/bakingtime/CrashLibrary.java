/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/3/17 2:44 PM
 */

package com.hendercine.android.bakingtime;

/**
 * BakinBuns created by hendercine on 10/3/17.
 */

public final class CrashLibrary {

    public static void log(int priority, String tag, String message) {
    // TODO add log entry to circular buffer.
}

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
    }

    private CrashLibrary() {
        throw new AssertionError("No instances.");
    }
}
