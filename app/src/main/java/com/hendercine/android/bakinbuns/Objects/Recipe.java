/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:45 PM
 */

package com.hendercine.android.bakinbuns.Objects;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Recipe {

    private int mRecipeId;
    private String mRecipeName;
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;

    public Recipe(int recipeId, String recipeName, ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        this.mRecipeId = recipeId;
        this.mRecipeName = recipeName;
        this.mIngredients = ingredients;
        this.mSteps = steps;
    }

    public int getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(int recipeId) {
        this.mRecipeId = recipeId;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public void setRecipeName(String recipeName) {
        this.mRecipeName = recipeName;
    }

    public ArrayList<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return mSteps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.mSteps = steps;
    }
}
