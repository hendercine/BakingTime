/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 9:02 AM
 */

package com.hendercine.android.bakinbuns.data.db;

import android.content.Context;

import com.hendercine.android.bakinbuns.data.db.model.DaoMaster;
import com.hendercine.android.bakinbuns.di.ApplicationContext;
import com.hendercine.android.bakinbuns.di.DatabaseInfo;
import com.hendercine.android.bakinbuns.utils.AppLogger;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        AppLogger.d("DEBUG",
                "DB_OLD_VERSION: " + oldVersion + ", DB_NEW_VERSION: " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
