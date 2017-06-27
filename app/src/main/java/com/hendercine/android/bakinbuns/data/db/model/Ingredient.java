/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 5:51 PM
 */

package com.hendercine.android.bakinbuns.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Entity(nameInDb = "ingredients")
public class Ingredient {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Expose
    @SerializedName("quantity")
    @Property(nameInDb = "quantity")
    private String ingredientQuantity;

    @Expose
    @SerializedName("measure")
    @Property(nameInDb = "measure")
    private String ingredientMeasure;

    @Expose
    @SerializedName("ingredient")
    @Property(nameInDb = "ingredient")
    private String ingredientName;

    @Expose
    @SerializedName("recipe_id")
    @Property(nameInDb = "recipe_id")
    private Long recipeId;

    @Generated(hash = 1293555273)
    public Ingredient(Long id, String ingredientQuantity, String ingredientMeasure,
                      String ingredientName, Long recipeId) {
        this.id = id;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientName = ingredientName;
        this.recipeId = recipeId;
    }

    @Generated(hash = 1338416856)
    public Ingredient() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientQuantity() {
        return this.ingredientQuantity;
    }

    public void setIngredientQuantity(String ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return this.ingredientMeasure;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }

    public String getIngredientName() {
        return this.ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Long getRecipeId() {
        return this.recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

}
