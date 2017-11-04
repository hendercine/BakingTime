/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/23/17 1:35 PM
 */

package com.hendercine.android.bakingtime.data.network;

import com.hendercine.android.bakingtime.data.models.Recipe;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * BakingTime created by hendercine on 7/24/17.
 */

interface RecipeService {

    @GET("android-baking-app-json") Observable<ArrayList<Recipe>> getRecipeData();
}
