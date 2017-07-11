/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:51 PM
 */

package com.hendercine.android.bakinbuns.Objects;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

class Ingredient {

    private int mQuantity;
    private String mMeasure;
    private String mIngredientName;

    public Ingredient(int quantity, String measure, String ingredientName) {
        this.mQuantity = quantity;
        this.mMeasure = measure;
        this.mIngredientName = ingredientName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public void setMeasure(String measure) {
        this.mMeasure = measure;
    }

    public String getIngredientName() {
        return mIngredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.mIngredientName = ingredientName;
    }
}
