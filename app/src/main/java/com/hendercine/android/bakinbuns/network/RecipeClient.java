///*
// * Created by James Henderson on 2017
// * Copyright (c) Hendercine Productions and James Henderson 2017.
// * All rights reserved.
// *
// * Last modified 7/25/17 6:17 PM
// */
//
//package com.hendercine.android.bakinbuns.network;
//
//import android.support.annotation.NonNull;
//
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.hendercine.android.bakinbuns.data.models.Recipe;
//
//import java.util.ArrayList;
//
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import rx.Observable;
//import rx.schedulers.Schedulers;
//
///**
// * BakinBuns created by hendercine on 7/25/17.
// */
//
//public class RecipeClient {
//
//    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking";
//
//    private static RecipeClient instance;
//    private static RecipeService recipeService;
//
//    private RecipeClient() {
//        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
//                .createWithScheduler(Schedulers.io());
//        final Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create();
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(rxAdapter)
//                .build();
//        recipeService = retrofit.create(RecipeService.class);
//    }
//
//    public static RecipeClient getInstance() {
//        if (instance == null) {
//            instance = new RecipeClient();
//        }
//        return instance;
//    }
//
//    public Observable<ArrayList<Recipe>> getRecipeJsonStr(@NonNull String recipeJsonStr) {
//        return recipeService.getRecipeData(recipeJsonStr);
//    }
//}
