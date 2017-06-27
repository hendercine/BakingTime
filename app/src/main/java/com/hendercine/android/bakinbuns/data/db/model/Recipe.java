/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 8:54 AM
 */

package com.hendercine.android.bakinbuns.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Entity(nameInDb = "recipe")
public class Recipe {

    @Expose
    @SerializedName("id")
    @Id
    private long id;

    @Expose
    @SerializedName("recipe_name")
    @Property(nameInDb = "recipe_name")
    private String recipeName;

    @ToMany(referencedJoinProperty = "recipeId")
    private List<RecipeStep> recipeIngredients;

    @ToMany(referencedJoinProperty = "recipeId")
    private List<RecipeStep> stepList;

}
