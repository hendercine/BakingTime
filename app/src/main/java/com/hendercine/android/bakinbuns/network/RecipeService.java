/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/24/17 12:46 PM
 */

package com.hendercine.android.bakinbuns.network;

import com.hendercine.android.bakinbuns.data.models.Ingredient;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * BakinBuns created by hendercine on 7/24/17.
 */

public interface RecipeService {

    String URL_ENDPOINT = "topher/2017/May/59121517_baking/baking.json";

    @GET(URL_ENDPOINT)
    Observable<List<Recipe>> getRecipes(@Path("id") int id);

    @GET(URL_ENDPOINT)
    Observable<List<Ingredient>> getIngredients(@Path("ingredients") String ingredients);

    @GET(URL_ENDPOINT)
    Observable<List<Step>> getSteps(@Path("steps") String steps);

    @GET(URL_ENDPOINT)
    Observable<List<Step>> getVideoUrl(@Path("videoURL") String videoURL);
}
