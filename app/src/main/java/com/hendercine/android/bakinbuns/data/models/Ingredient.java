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

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

        // Empty constructor needed by the Parceler library
    public Ingredient() {
    }

    public Ingredient(int quantity, String measure, String ingredientName) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredientName = ingredientName;
    }

    //@AutoValue
//public abstract class Ingredient implements Parcelable {
//    public abstract int quantity();
//    public abstract String measure();
//    public abstract String ingredientName();
//
//    public static Ingredient create(int quantity, String measure, String ingredientName) {
//        return builder()
//                .quantity(quantity)
//                .measure(measure)
//                .ingredientName(ingredientName)
//                .build();
//    }
//
//    @AutoValue.Builder
//    public interface Builder {
//        Builder quantity(int x);
//        Builder measure(String x);
//        Builder ingredientName(String x);
//        Ingredient build();
//    }
//
//    public static Builder builder() {
//        return new AutoValue_Ingredient.Builder();
//    }
}
