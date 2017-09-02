/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:45 PM
 */

package com.hendercine.android.bakinbuns.data.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@Parcel
public class Recipe {

        // Fields must be public
    @SerializedName("id")
    public int recipeId;

    @SerializedName("name")
    public String recipeName;

    @SerializedName("servings")
    public int servings;

    @SerializedName("step_key")
    public String stepKey;

    @SerializedName("step_list")
    public ArrayList<Step> stepList;

    @SerializedName("ingredient_key")
    public String ingredientKey;

    @SerializedName("ingredient_list")
    public ArrayList<Ingredient> ingredientList;

        // Empty constructor needed by the Parceler library
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

    public String getStepKey() {
        return stepKey;
    }

    public void setStepKey(String stepKey) {
        this.stepKey = stepKey;
    }

    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public ArrayList<Step> setStepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
        return null;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getIngredientKey() {
        return ingredientKey;
    }

    public void setIngredientKey(String ingredientKey) {
        this.ingredientKey = ingredientKey;
    }
}
