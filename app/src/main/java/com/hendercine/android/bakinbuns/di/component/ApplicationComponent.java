/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 10:31 AM
 */

package com.hendercine.android.bakinbuns.di.component;

import android.app.Application;
import android.content.Context;

import com.hendercine.android.bakinbuns.BakinBunsApp;
import com.hendercine.android.bakinbuns.data.DataManager;
import com.hendercine.android.bakinbuns.di.ApplicationContext;
import com.hendercine.android.bakinbuns.di.module.ApplicationModule;
import com.hendercine.android.bakinbuns.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BakinBunsApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
