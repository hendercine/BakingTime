/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 8:56 AM
 */

package com.hendercine.android.bakinbuns.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Entity(nameInDb = "steps")
public class RecipeStep {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Expose
    @SerializedName("short_description")
    @Property(nameInDb = "short_description")
    private String shortDescription;

    @Expose
    @SerializedName("description")
    @Property(nameInDb = "description")
    private String description;

    @Expose
    @SerializedName("video_url")
    @Property(nameInDb = "video_url")
    private String videoUrl;

    @Expose
    @SerializedName("thumbnail_url")
    @Property(nameInDb = "thumbnail_url")
    private String thumbnailUrl;

    @Expose
    @SerializedName("recipe_id")
    @Property(nameInDb = "recipe_id")
    private Long recipeId;

}
