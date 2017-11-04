/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/23/17 1:35 PM
 */

package com.hendercine.android.bakingtime.data.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 * BakingTime created by hendercine on 7/10/17.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Step implements Serializable {

    // Fields must be public for Parceler.
    @SuppressWarnings("WeakerAccess")
    @SerializedName("id")
    public int stepId;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("shortDescription")
    public String shortDescription;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("description")
    public String description;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("videoURL")
    public String videoURL;

    @SuppressWarnings("WeakerAccess")
    @SerializedName("thumbnailURL")
    public String thumbnailURL;

    // Empty constructor needed by the Parceler library
    public Step() {
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
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
}
