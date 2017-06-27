/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/23/17 5:08 PM
 */

package com.hendercine.android.bakinbuns.data;

import android.content.Context;

import com.hendercine.android.bakinbuns.data.db.DbHelper;
import com.hendercine.android.bakinbuns.data.network.ApiHelper;
import com.hendercine.android.bakinbuns.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * BakinBuns created by hendercine on 6/23/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDdbHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDdbHelper = dbHelper;
        mApiHelper = apiHelper;
    }
}
