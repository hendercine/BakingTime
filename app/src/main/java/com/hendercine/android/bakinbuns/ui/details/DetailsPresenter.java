/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 4:00 PM
 */

package com.hendercine.android.bakinbuns.ui.details;

import android.support.annotation.StringRes;

import com.hendercine.android.bakinbuns.data.DataManager;
import com.hendercine.android.bakinbuns.ui.base.BasePresenter;
import com.hendercine.android.bakinbuns.ui.main.MainPresenter;
import com.hendercine.android.bakinbuns.ui.base.MvpView;
import com.hendercine.android.bakinbuns.ui.steps.StepsMvpView;
import com.hendercine.android.bakinbuns.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public class DetailsPresenter<V extends StepsMvpView> extends BasePresenter<V>
        implements DetailsMvpView<V> {

    @Inject
    public DetailsPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
