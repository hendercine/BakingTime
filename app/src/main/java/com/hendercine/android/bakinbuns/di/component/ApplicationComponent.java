/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 10:31 AM
 */

package com.hendercine.android.bakinbuns.di.component;

import com.hendercine.android.bakinbuns.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
}
