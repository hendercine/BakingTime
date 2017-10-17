/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/17/17 2:50 PM
 */

package com.hendercine.android.bakingtime.utils;

import android.support.test.espresso.IdlingResource;

import com.hendercine.android.bakingtime.ui.StepsListActivity;

import static com.hendercine.android.bakingtime.ui.MainSelectionActivity.ProgressListener;

/**
 * BakingTime created by hendercine on 10/17/17.
 */

public class StepsListActivityIdlingResource implements IdlingResource {

    private StepsListActivity mActivity;
    private volatile ResourceCallback mCallback;
//    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    public StepsListActivityIdlingResource(StepsListActivity activity) {
        this.mActivity = activity;

        ProgressListener mProgressListener = new ProgressListener() {
            @Override
            public void onProgressShown() {
            }

            @Override
            public void onProgressHidden() {
                if (mCallback == null) {
                    return;
                }
                mCallback.onTransitionToIdle();
            }
        };

        mActivity.setProgressListener(mProgressListener);
    }

    @Override
    public String getName() {
        return "MainSelectionActivityIdleName";
    }

    @Override
    public boolean isIdleNow() {
        return !mActivity.isInProgress();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

//    /**
//     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
//     * @param isIdleNow false if there are pending operations, true if idle.
//     */
//    public void setIdleState(boolean isIdleNow) {
//        mIsIdleNow.set(isIdleNow);
//        if (isIdleNow && mCallback != null) {
//            mCallback.onTransitionToIdle();
//        }
//    }

}
