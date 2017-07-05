/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 9:32 AM
 */

package com.hendercine.android.bakinbuns.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.hendercine.android.bakinbuns.data.db.model.Step;
import com.hendercine.android.bakinbuns.di.ActivityContext;
import com.hendercine.android.bakinbuns.di.PerActivity;
import com.hendercine.android.bakinbuns.ui.main.MainMvpPresenter;
import com.hendercine.android.bakinbuns.ui.main.MainMvpView;
import com.hendercine.android.bakinbuns.ui.main.MainPresenter;
import com.hendercine.android.bakinbuns.ui.steps.StepsAdapter;
import com.hendercine.android.bakinbuns.ui.steps.StepsMvpPresenter;
import com.hendercine.android.bakinbuns.ui.steps.StepsMvpView;
import com.hendercine.android.bakinbuns.ui.steps.StepsPresenter;
import com.hendercine.android.bakinbuns.utils.rx.AppSchedulerProvider;
import com.hendercine.android.bakinbuns.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    StepsMvpPresenter<StepsMvpView> provideStepsPresenter(
            StepsPresenter<StepsMvpView> presenter) {
        return presenter;
    }

    // TODO: Modify DetailsPresenter and Mvps to uncomment the following code
//    @Provides
//    DetailsMvpPresenter<DetailsMvpView> provideDetailsPresenter(
//            DetailsPresenter<DetailsMvpView> presenter) {
//        return presenter;
//    }

    @Provides
    StepsAdapter provideStepsAdapter() {
        return new StepsAdapter(new ArrayList<Step>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
