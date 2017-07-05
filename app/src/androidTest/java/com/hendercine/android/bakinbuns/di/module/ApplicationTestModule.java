/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/4/17 5:23 PM
 */

package com.hendercine.android.bakinbuns.di.module;

import android.app.Application;
import android.content.Context;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.AppDataManager;
import com.hendercine.android.bakinbuns.data.DataManager;
import com.hendercine.android.bakinbuns.data.db.AppDbHelper;
import com.hendercine.android.bakinbuns.data.db.DbHelper;
import com.hendercine.android.bakinbuns.data.network.ApiHeader;
import com.hendercine.android.bakinbuns.data.network.ApiHelper;
import com.hendercine.android.bakinbuns.data.network.AppApiHelper;
import com.hendercine.android.bakinbuns.di.ApiInfo;
import com.hendercine.android.bakinbuns.di.ApplicationContext;
import com.hendercine.android.bakinbuns.di.DatabaseInfo;
import com.hendercine.android.bakinbuns.test.BuildConfig;
import com.hendercine.android.bakinbuns.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * BakinBuns created by hendercine on 7/4/17.
 */

@Module
public class ApplicationTestModule {

    private final Application mApplication;


    public ApplicationTestModule(Application application) {
        this.mApplication = application;
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
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    // TODO: Mock all below for UI testing

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
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey);
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
