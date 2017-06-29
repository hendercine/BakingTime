/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 11:00 AM
 */

package com.hendercine.android.bakinbuns.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public abstract class BaseSubView extends ViewGroup implements SubMvpView {

    private MvpView mParentMvpView;

    public BaseSubView(Context context) {
        super(context);
    }

    public BaseSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public BaseSubView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void attachParentMvpView(MvpView mvpView) {
        mParentMvpView = mvpView;
    }

    @Override
    public void showLoading() {
        if (mParentMvpView != null) {
            mParentMvpView.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mParentMvpView != null) {
            mParentMvpView.hideLoading();
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mParentMvpView != null) {
            mParentMvpView.onError(resId);
        }
    }

    @Override
    public void onError(String message) {
        if (mParentMvpView != null) {
            mParentMvpView.onError(message);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mParentMvpView != null) {
            mParentMvpView.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mParentMvpView != null) {
            mParentMvpView.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return mParentMvpView != null && mParentMvpView.isNetworkConnected();
    }

    protected abstract void bindViewsAndSetOnClickListeners();

    protected abstract void setUp();
}
