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

    @SerializedName("quantity")
    public int ingredientQuantity;

    @SerializedName("measure")
    public String ingredientMeasure;

    @SerializedName("ingredient")
    public String ingredientName;

    @SerializedName("shortDescription")
    public String shortDescription;

    @SerializedName("description")
    public String description;

    @SerializedName("videoURL")
    public String videoURL;

    @SerializedName("thumbnailURL")
    public String thumbnailURL;

    @SerializedName("servings")
    public int servings;

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

    public int getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(int ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
