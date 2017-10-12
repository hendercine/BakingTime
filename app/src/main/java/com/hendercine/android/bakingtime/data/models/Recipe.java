/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:45 PM
 */

package com.hendercine.android.bakingtime.data.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Recipe {

    // Fields must be public for Parceler.
    @SuppressWarnings("WeakerAccess")
    @SerializedName("ingredient_name")
    public int recipeId;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("name")
    public String recipeName;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("servings")
    public int servings;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("steps")
    public ArrayList<Step> stepList;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("ingredients")
    public ArrayList<Ingredient> ingredientList;

    // Empty constructor needed by the Parceler library.
    public Recipe() {
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }


    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

}
