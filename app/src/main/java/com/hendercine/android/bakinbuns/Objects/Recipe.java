/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:45 PM
 */

package com.hendercine.android.bakinbuns.Objects;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@Parcel
public class Recipe {

    // Fields must be public
    int recipeId;
    String recipeName;
    ArrayList<Ingredient> ingredients;
    ArrayList<Step> steps;
    int servings;

    // Empty constructor needed by the Parceler library
    public Recipe () {
    }

    public Recipe(int recipeId, String recipeName, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}