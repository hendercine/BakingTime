/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 9:31 AM
 */

package com.hendercine.android.bakinbuns.di.module;

import android.app.Application;
import android.content.Context;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.AppDataManager;
import com.hendercine.android.bakinbuns.data.DataManager;
import com.hendercine.android.bakinbuns.data.db.AppDbHelper;
import com.hendercine.android.bakinbuns.data.db.DbHelper;
import com.hendercine.android.bakinbuns.di.ApplicationContext;
import com.hendercine.android.bakinbuns.di.DatabaseInfo;
import com.hendercine.android.bakinbuns.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath
                        ("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
