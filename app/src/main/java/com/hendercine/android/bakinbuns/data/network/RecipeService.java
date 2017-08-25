/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/24/17 12:46 PM
 */

package com.hendercine.android.bakinbuns.data.network;

import com.hendercine.android.bakinbuns.data.models.Recipe;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * BakinBuns created by hendercine on 7/24/17.
 */

public interface RecipeService {

    @GET("android-baking-app-json") Observable<List<Recipe>> getRecipeData();
}
