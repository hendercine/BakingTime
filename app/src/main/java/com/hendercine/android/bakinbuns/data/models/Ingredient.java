/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:51 PM
 */

package com.hendercine.android.bakinbuns.data.models;

import org.parceler.Parcel;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@Parcel
public class Ingredient {

    int quantity;
    String measure;
    String ingredientName;

    // Empty constructor needed by the Parceler library
    public Ingredient() {
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
