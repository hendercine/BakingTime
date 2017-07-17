/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 5:51 PM
 */

package com.hendercine.android.bakinbuns.Objects;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */
@AutoValue
public abstract class Ingredient implements Parcelable {

    public abstract int quantity();
    public abstract String measure();
    public abstract String ingredientName();

    public static Ingredient create(int quantity, String measure, String ingredientName) {
        return builder()
                .quantity(quantity)
                .measure(measure).
                ingredientName(ingredientName)
                .build();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder quantity(int x);
        Builder measure(String x);
        Builder ingredientName(String x);
        Ingredient build();
    }

    public static Builder builder() {
        return new AutoValue_Ingredient.Builder();
    }
}
