/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 8:47 AM
 */

package com.hendercine.android.bakinbuns.data.db;

import com.hendercine.android.bakinbuns.data.db.model.Ingredient;
import com.hendercine.android.bakinbuns.data.db.model.Recipe;
import com.hendercine.android.bakinbuns.data.db.model.RecipeStep;

import java.util.List;

import io.reactivex.Observable;


/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public interface DbHelper {

    Observable<List<Recipe>> getAllRecipes();

    Observable<List<RecipeStep>> getAllRecipeSteps();

    Observable<List<Ingredient>> getAllIngredients();

    Observable<Boolean> isRecipeEmpty();

    Observable<Boolean> isRecipeStepEmpty();

    Observable<Boolean> isIngredientEmpty();

    Observable<Boolean> saveRecipe();

    Observable<Boolean> saveRecipeStep();

    Observable<Boolean> saveIngredient();

    Observable<Boolean> saveRecipeList();

    Observable<Boolean> saveRecipeStepList();

    Observable<Boolean> saveIngredientList();
}
