/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 9:33 AM
 */

package com.hendercine.android.bakinbuns.di.module;

import android.app.Service;

import dagger.Module;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) { mService = service; }
}
