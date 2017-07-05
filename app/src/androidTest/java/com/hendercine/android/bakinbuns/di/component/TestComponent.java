/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/4/17 5:21 PM
 */

package com.hendercine.android.bakinbuns.di.component;

import com.hendercine.android.bakinbuns.di.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * BakinBuns created by hendercine on 7/4/17.
 */

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {
}
