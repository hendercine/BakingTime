/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:51 PM
 */

package com.hendercine.android.bakingtime.data.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Ingredient {

    // Fields must be public for Parceler.
    @SuppressWarnings("WeakerAccess")
    @SerializedName("quantity")
    public float ingredientQuantity;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("measure")
    public String ingredientMeasure;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("ingredient")
    public String ingredientName;

    // Empty constructor needed by the Parceler library
    public Ingredient() {
    }

    public float getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(float ingredientQuantity) {
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
}
