/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 3:49 PM
 */

package com.hendercine.android.bakinbuns.ui.steps;

import com.hendercine.android.bakinbuns.di.PerActivity;
import com.hendercine.android.bakinbuns.ui.base.MvpPresenter;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@PerActivity
public interface StepsMvpPresenter<V extends StepsMvpView> extends
        MvpPresenter<V> {
    void onViewPrepared();
}
