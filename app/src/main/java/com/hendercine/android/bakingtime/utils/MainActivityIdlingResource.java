package com.hendercine.android.bakingtime.utils;

import android.support.test.espresso.IdlingResource;

import com.hendercine.android.bakingtime.ui.MainSelectionActivity;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * BakingTime created by hendercine on 10/13/17.
 */

public class MainActivityIdlingResource implements IdlingResource {

    private MainSelectionActivity mActivity;
    private volatile ResourceCallback mCallback;
    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

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

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }

}
