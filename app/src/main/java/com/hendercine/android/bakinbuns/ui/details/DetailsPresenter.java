/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 4:00 PM
 */

package com.hendercine.android.bakinbuns.ui.details;

import android.support.annotation.StringRes;

import com.hendercine.android.bakinbuns.ui.base.BasePresenter;
import com.hendercine.android.bakinbuns.ui.main.MainPresenter;
import com.hendercine.android.bakinbuns.ui.base.MvpView;
import com.hendercine.android.bakinbuns.ui.steps.StepsMvpView;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public class DetailsPresenter<V extends StepsMvpView> extends BasePresenter<V>
        implements MvpView<V> {

    @Override
    public void onAttach(V mvpView) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(@StringRes int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(@StringRes int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
