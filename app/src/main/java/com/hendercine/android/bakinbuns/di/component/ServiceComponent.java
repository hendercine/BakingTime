/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 10:35 AM
 */

package com.hendercine.android.bakinbuns.di.component;

import com.hendercine.android.bakinbuns.di.PerService;
import com.hendercine.android.bakinbuns.di.module.ServiceModule;

import dagger.Component;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);
}
