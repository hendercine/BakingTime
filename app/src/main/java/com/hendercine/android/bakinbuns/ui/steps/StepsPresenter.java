/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 3:54 PM
 */

package com.hendercine.android.bakinbuns.ui.steps;

import com.androidnetworking.error.ANError;
import com.hendercine.android.bakinbuns.data.DataManager;
import com.hendercine.android.bakinbuns.data.network.model.StepResponse;
import com.hendercine.android.bakinbuns.ui.base.BasePresenter;
import com.hendercine.android.bakinbuns.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public class StepsPresenter<V extends StepsMvpView> extends BasePresenter<V>
        implements StepsMvpPresenter<V> {

    @Inject
    public StepsPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getStepApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<StepResponse>() {
                    @Override
                    public void accept(@NonNull StepResponse stepResponse)
                            throws Exception {
                        if (stepResponse != null && stepResponse.getData() !=
                                null) {
                            getMvpView().updateStep(stepResponse.getData());
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));

    }
}
