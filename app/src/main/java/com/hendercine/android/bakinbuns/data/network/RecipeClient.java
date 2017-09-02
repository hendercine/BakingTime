/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/25/17 6:17 PM
 */

package com.hendercine.android.bakinbuns.data.network;

import com.hendercine.android.bakinbuns.data.models.Recipe;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * BakinBuns created by hendercine on 7/25/17.
 */

public class RecipeClient {

    private static final String BASE_URL = "http://go.udacity.com/";

    private static RecipeClient instance;
    private static RecipeService recipeService;

    private RecipeClient() {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        recipeService = retrofit.create(RecipeService.class);
    }

    public static RecipeClient getInstance() {
        if (instance == null) {
            instance = new RecipeClient();
        }
        return instance;
    }

    public Observable<ArrayList<Recipe>> getRecipeFromJson() {
        return recipeService.getRecipeData();
    }
}
