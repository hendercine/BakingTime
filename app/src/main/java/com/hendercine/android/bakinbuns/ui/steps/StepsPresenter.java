/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 3:54 PM
 */

package com.hendercine.android.bakinbuns.ui.steps;

import com.hendercine.android.bakinbuns.ui.base.BasePresenter;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public class StepsPresenter<V extends StepsMvpView> extends BasePresenter<V>
        implements StepsMvpPresenter<V> {
    public StepsPresenter() {
        super(mDataManager, mSchedulerProvider, mCompositeDisposable);
    }
}
