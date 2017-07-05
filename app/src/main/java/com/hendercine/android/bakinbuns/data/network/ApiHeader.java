/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 9:20 AM
 */

package com.hendercine.android.bakinbuns.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hendercine.android.bakinbuns.di.ApiInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Singleton
public class ApiHeader {

    private PublicApiHeader mPublicApiHeader;
    private ProtectedApiHeader mProtectedApiHeader;

    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        this.mPublicApiHeader = publicApiHeader;
        this.mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class PublicApiHeader {
        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        @Inject
        public PublicApiHeader(@ApiInfo String apiKey) {
            mApiKey = apiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        public ProtectedApiHeader(String mApiKey) {
            this.mApiKey = mApiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            this.mApiKey = apiKey;
        }
    }
}
