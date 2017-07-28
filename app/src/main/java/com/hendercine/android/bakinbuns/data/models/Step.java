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
public class Step {

    int stepId;
    String shortDescription;
    String description;
    String videoUrl;
    String thumbnailUrl;

    public int getStepId() {
        return stepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

        // Empty constructor needed by the Parceler library
    public Step() {
    }

//    public Step(int stepId, String shortDescription, String description,
//                String videoUrl, String thumbnailUrl) {
//        this.stepId = stepId;
//        this.shortDescription = shortDescription;
//        this.description = description;
//        this.videoUrl = videoUrl;
//        this.thumbnailUrl = thumbnailUrl;
//    }
//
//    public int getStepId() {
//        return stepId;
//    }
//
//    public void setStepId(int stepId) {
//        this.stepId = stepId;
//    }
//
//    public String getShortDescription() {
//        return shortDescription;
//    }
//
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getVideoUrl() {
//        return videoUrl;
//    }
//
//    public void setVideoUrl(String videoUrl) {
//        this.videoUrl = videoUrl;
//    }
//
//    public String getThumbnailUrl() {
//        return thumbnailUrl;
//    }
//
//    public void setThumbnailUrl(String thumbnailUrl) {
//        this.thumbnailUrl = thumbnailUrl;
//    }
}
