/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/24/17 4:50 PM
 */

package com.hendercine.android.bakinbuns.network;

import com.hendercine.android.bakinbuns.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * BakinBuns created by hendercine on 7/24/17.
 */

public class FakeDataProvider {
    public static List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Recipe recipe = new Recipe();
            recipe.recipeId = i;
            recipe.recipeName = String.valueOf(i);
            recipe.servings = String.valueOf(i);
            recipes.add(recipe);
        }
        return recipes;
    }
}
