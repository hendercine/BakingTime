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

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

//@AutoValue
//public abstract class Recipe implements Parcelable {
//
//    public abstract int recipeId();
//    public abstract String recipeName();
//    public abstract ArrayList<Ingredient> ingredients();
//    public abstract ArrayList<Step> steps();
//    public abstract int servings();
//
//    public static Recipe create(int recipeId, ArrayList<Ingredient> ingredients,
//                                String recipeName,
//                                ArrayList<Step> steps, int servings) {
//        return builder()
//                .recipeId(recipeId)
//                .recipeName(recipeName)
//                .ingredients(ingredients)
//                .steps(steps)
//                .servings(servings)
//                .build();
//    }
//
//    public static Builder builder() {
//        return new AutoValue_Recipe.Builder();
//    }
//
//    @AutoValue.Builder
//    public interface Builder {
//
//        Builder recipeId(int x);
//        Builder recipeName(String s);
//        Builder ingredients(ArrayList<Ingredient> g);
//        Builder steps(ArrayList<Step> p);
//        Builder servings(int x);
//
//        Recipe build();
//    }

@Parcel
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

        // Empty constructor needed by the Parceler library
    public Recipe () {
    }

    public Recipe(int recipeId, String recipeName, Ingredient ingredients, Step steps, int servings) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }
}
