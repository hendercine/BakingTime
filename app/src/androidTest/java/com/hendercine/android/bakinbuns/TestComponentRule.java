/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/4/17 5:33 PM
 */

package com.hendercine.android.bakinbuns;

import android.content.Context;

import com.hendercine.android.bakinbuns.data.DataManager;
import com.hendercine.android.bakinbuns.di.component.TestComponent;
import com.hendercine.android.bakinbuns.di.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * BakinBuns created by hendercine on 7/4/17.
 */

public class TestComponentRule implements TestRule {

    private TestComponent mTestComponent;
    private Context mContext;

    public TestComponentRule(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public DataManager getDataManager() {
        return mTestComponent.getDataManager();
    }

    private void setupDaggerTestComponentInApplication() {
        BakinBunsApp application = (BakinBunsApp) mContext.getApplicationContext();
        mTestComponent = DaggerTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
        application.setComponent(mTestComponent);
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
