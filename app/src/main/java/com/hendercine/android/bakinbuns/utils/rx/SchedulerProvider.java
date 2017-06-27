/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 4:28 PM
 */

package com.hendercine.android.bakinbuns.utils.rx;

import io.reactivex.Scheduler;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
