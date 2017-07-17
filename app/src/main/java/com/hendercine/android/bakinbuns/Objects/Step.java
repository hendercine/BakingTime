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

import retrofit2.http.Url;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

@AutoValue
public abstract class Step implements Parcelable {

    public abstract int stepId();
    public abstract String shortDescription();
    public abstract String description();
    public abstract Url videoUrl();
    public abstract Url thumbnailUrl();

    public static Step create(int stepId, String shortDescription, String
            description, Url videoUrl, Url thumbnailUrl) {
        return builder()
                .stepId(stepId)
                .shortDescription(shortDescription)
                .description(description)
                .videoUrl(videoUrl)
                .thumbnailUrl(thumbnailUrl)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_Step.Builder();
    }

    @AutoValue.Builder
    public interface Builder {
        Builder stepId(int x);
        Builder shortDescription(String s);
        Builder description(String s);
        Builder videoUrl(Url v);
        Builder thumbnailUrl(Url i);
        Step build();
    }
}
