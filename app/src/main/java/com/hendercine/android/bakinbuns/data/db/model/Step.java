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
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Entity(nameInDb = "steps")
public class Step {

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

    @Generated(hash = 1570994513)
    public Step(Long id, String shortDescription, String description,
                String videoUrl, String thumbnailUrl, Long recipeId) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.recipeId = recipeId;
    }

    @Generated(hash = 540939657)
    public Step() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getRecipeId() {
        return this.recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Keep
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Step)) return false;

        Step step = (Step) obj;

        if (!id.equals(step.id)) return false;
        if (!shortDescription.equals(step.shortDescription)) return false;
        if (!description.equals(step.description)) return false;
        if (!videoUrl.equals(step.videoUrl)) return false;
        if (!thumbnailUrl.equals(step.thumbnailUrl)) return false;
        return recipeId.equals(step.recipeId);
    }

    @Keep
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + shortDescription.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + videoUrl.hashCode();
        result = 31 * result + thumbnailUrl.hashCode();
        result = 31 * result + recipeId.hashCode();
        return result;
    }
}
