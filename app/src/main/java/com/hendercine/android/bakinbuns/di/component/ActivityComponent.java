/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 10:30 AM
 */

package com.hendercine.android.bakinbuns.di.component;

import com.hendercine.android.bakinbuns.di.PerActivity;
import com.hendercine.android.bakinbuns.di.module.ActivityModule;
import com.hendercine.android.bakinbuns.ui.details.DetailFragment;
import com.hendercine.android.bakinbuns.ui.main.MainSelectionActivity;
import com.hendercine.android.bakinbuns.ui.steps.StepsActivity;

import dagger.Component;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainSelectionActivity activity);

    void inject(StepsActivity activity);

    void inject(DetailFragment fragment);

}
