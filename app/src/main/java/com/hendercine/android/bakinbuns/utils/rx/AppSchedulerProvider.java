/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 4:30 PM
 */

package com.hendercine.android.bakinbuns.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public class AppSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler ui() { return AndroidSchedulers.mainThread(); }

    @Override
    public Scheduler computation() { return Schedulers.computation(); }

    @Override
    public Scheduler io() { return Schedulers.io(); }
}
