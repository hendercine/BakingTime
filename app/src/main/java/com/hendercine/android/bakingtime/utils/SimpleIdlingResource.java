/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 11/1/17 4:48 PM
 */

package com.hendercine.android.bakingtime.utils;

/**
 * BakingTime created by Hendercine on 11/1/17.
 * <p>
 * A very simple implementation of {@link android.support.test.espresso.IdlingResource}.
 * <p>
 * Consider using CountingIdlingResource from espresso-contrib package if you use this class from
 * multiple threads or need to keep a count of pending operations.
 */

public class SimpleIdlingResource implements android.support.test.espresso.IdlingResource {

    private ResourceCallback mCallback;
    private final long startTime;
    private final long waitingTime;

    public SimpleIdlingResource(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitingTime;
    }

    @Override
    public String getName() {
        return this.getClass().getName() + ":" + waitingTime;
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitingTime);
        if (idle) {
            mCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.mCallback = callback;
    }
}
