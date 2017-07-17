/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:45 PM
 */

package com.hendercine.android.bakinbuns.Objects;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@AutoValue
public abstract class Recipe implements Parcelable {
    public abstract int recipeId();

    public abstract String recipeName();

    public abstract ArrayList<Ingredient> ingredients();

    public abstract ArrayList<Step> steps();

    public abstract int servings();

    public static Recipe create(int recipeId, String recipeName,
                                ArrayList<Ingredient> ingredients,
                                ArrayList<Step> steps, int servings) {
        return builder()
                .recipeId(recipeId)
                .recipeName(recipeName)
                .ingredients(ingredients)
                .steps(steps)
                .servings(servings)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_Recipe.Builder();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder recipeId(int x);

        Builder recipeName(String s);

        Builder ingredients(ArrayList<Ingredient> g);

        Builder steps(ArrayList<Step> p);

        Builder servings(int x);

        Recipe build();
    }

}