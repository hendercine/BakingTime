/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 9:07 AM
 */

package com.hendercine.android.bakinbuns.data.network;

import com.hendercine.android.bakinbuns.data.network.model.IngredientResponse;
import com.hendercine.android.bakinbuns.data.network.model.StepResponse;

import io.reactivex.Observable;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<StepResponse> getStepApiCall();

    Observable<IngredientResponse> getIngredientApiCall();

}
