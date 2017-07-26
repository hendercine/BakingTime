/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:45 PM
 */

package com.hendercine.android.bakinbuns.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Recipe {

        // Fields must be public
    @SerializedName("id")
    public int recipeId;

    @SerializedName("name")
    public String recipeName;

    @SerializedName("ingredients")
    public Ingredient ingredients;

    @SerializedName("steps")
    public Step steps;

    @SerializedName("servings")
    public int servings;

//    @Override
//    public String toString() {
//        return(recipeName);
//    }
//
//    public int getRecipeId() {
//        return recipeId;
//    }
//
//    public void setRecipeId(int recipeId) {
//        this.recipeId = recipeId;
//    }
//
//    public String getRecipeName() {
//        return recipeName;
//    }
//
//    public Ingredient getIngredients() {
//        return ingredients;
//    }
//
//    public Step getSteps() {
//        return steps;
//    }
//
//    public String getServings() {
//        return servings;
//    }

    //    // Empty constructor needed by the Parceler library
//    public Recipe () {
//    }
//
    public Recipe(int recipeId, String recipeName, Ingredient ingredients, Step steps, int servings) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }
//
//    public int getRecipeId() {
//        return recipeId;
//    }
//
//    public void setRecipeId(int recipeId) {
//        this.recipeId = recipeId;
//    }
//
//    public String getRecipeName() {
//        return recipeName;
//    }
//
//    public void setRecipeName(String recipeName) {
//        this.recipeName = recipeName;
//    }
//
//    public ArrayList<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(ArrayList<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public ArrayList<Step> getSteps() {
//        return steps;
//    }
//
//    public void setSteps(ArrayList<Step> steps) {
//        this.steps = steps;
//    }
//
//    public int getServings() {
//        return servings;
//    }
//
//    public void setServings(int servings) {
//        this.servings = servings;
//    }
//
//    private Observer<Boolean> _getObserver() {
//
//        return new Observer<Boolean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Timber.e(e);
//            }
//
//            @Override
//            public void onNext(Boolean aBoolean) {
//
//            }
//        };
//    }
}