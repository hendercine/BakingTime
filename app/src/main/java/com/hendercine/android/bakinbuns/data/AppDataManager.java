/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/23/17 5:08 PM
 */

package com.hendercine.android.bakinbuns.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.hendercine.android.bakinbuns.data.db.DbHelper;
import com.hendercine.android.bakinbuns.data.db.model.Ingredient;
import com.hendercine.android.bakinbuns.data.db.model.Recipe;
import com.hendercine.android.bakinbuns.data.db.model.Step;
import com.hendercine.android.bakinbuns.di.ApplicationContext;
import com.hendercine.android.bakinbuns.utils.AppConstants;
import com.hendercine.android.bakinbuns.utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * BakinBuns created by hendercine on 6/23/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDdbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper) {
        mContext = context;
        mDdbHelper = dbHelper;
    }

    @Override
    public Observable<Boolean> addDatabaseRecipes() {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mDdbHelper.isRecipeEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(@NonNull Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types.newParameterizedTypeWithOwner
                                    (null, List.class, Recipe.class);
                            List<Recipe> recipeList = gson.fromJson(CommonUtils
                                            .loadJSONFromUrl(mContext,
                                                    AppConstants.RECIPE_JSON_URL),
                                    type);

                            return saveRecipeList(recipeList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> addDatabaseRecipeSteps() {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mDdbHelper.isRecipeStepEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(@NonNull Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types.newParameterizedTypeWithOwner
                                    (null, List.class, Step.class);
                            List<Step> stepList = gson.fromJson(CommonUtils
                                            .loadJSONFromUrl(mContext,
                                                    AppConstants.RECIPE_JSON_URL),
                                    type);

                            return saveRecipeStepList(stepList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> addDatabaseIngredients() {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mDdbHelper.isIngredientEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(@NonNull Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types.newParameterizedTypeWithOwner
                                    (null, List.class, Ingredient.class);
                            List<Ingredient> ingredientList = gson.fromJson(CommonUtils
                                            .loadJSONFromUrl(mContext,
                                                    AppConstants.RECIPE_JSON_URL),
                                    type);

                            return saveIngredientList(ingredientList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<List<Recipe>> getAllRecipes() {
        return mDdbHelper.getAllRecipes();
    }

    @Override
    public Observable<List<Step>> getAllRecipeSteps() {
        return mDdbHelper.getAllRecipeSteps();
    }

    @Override
    public Observable<List<Ingredient>> getAllIngredients() {
        return mDdbHelper.getAllIngredients();
    }

    @Override
    public Observable<Boolean> isRecipeEmpty() {
        return mDdbHelper.isRecipeEmpty();
    }

    @Override
    public Observable<Boolean> isRecipeStepEmpty() {
        return mDdbHelper.isRecipeStepEmpty();
    }

    @Override
    public Observable<Boolean> isIngredientEmpty() {
        return mDdbHelper.isIngredientEmpty();
    }

    @Override
    public Observable<Boolean> saveRecipe(Recipe recipe) {
        return mDdbHelper.saveRecipe(recipe);
    }

    @Override
    public Observable<Boolean> saveRecipeStep(Step step) {
        return mDdbHelper.saveRecipeStep(step);
    }

    @Override
    public Observable<Boolean> saveIngredient(Ingredient ingredient) {
        return mDdbHelper.saveIngredient(ingredient);
    }

    @Override
    public Observable<Boolean> saveRecipeList(List<Recipe> recipeList) {
        return mDdbHelper.saveRecipeList(recipeList);
    }

    @Override
    public Observable<Boolean> saveRecipeStepList(List<Step> stepList) {
        return mDdbHelper.saveRecipeStepList(stepList);
    }

    @Override
    public Observable<Boolean> saveIngredientList(List<Ingredient> ingredientList) {
        return mDdbHelper.saveIngredientList(ingredientList);
    }

}
