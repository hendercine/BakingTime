/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/3/17 2:03 PM
 */

package com.hendercine.android.bakinbuns.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hendercine.android.bakinbuns.data.db.model.Step;

import java.util.List;

/**
 * BakinBuns created by hendercine on 7/3/17.
 */

public class StepResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Step> data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Step> getData() {
        return data;
    }

    public void setData(List<Step> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StepResponse)) return false;

        StepResponse that = (StepResponse) obj;

        if (!statusCode.equals(that.statusCode)) return false;
        if (!message.equals(that.message)) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = statusCode.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
