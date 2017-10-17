package com.hendercine.android.bakingtime.utils;

import android.support.test.espresso.IdlingResource;

import com.hendercine.android.bakingtime.ui.MainSelectionActivity;

/**
 * BakingTime created by hendercine on 10/13/17.
 */

public class MainActivityIdlingResource implements IdlingResource {

    private MainSelectionActivity mActivity;
    private volatile ResourceCallback mCallback;

    public MainActivityIdlingResource(MainSelectionActivity activity) {
        this.mActivity = activity;

        MainSelectionActivity.ProgressListener mProgressListener = new MainSelectionActivity.ProgressListener() {
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

}
