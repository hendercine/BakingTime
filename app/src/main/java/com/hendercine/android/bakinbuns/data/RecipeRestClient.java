/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/19/17 4:55 PM
 */

package com.hendercine.android.bakinbuns.REST;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * BakinBuns created by hendercine on 6/19/17.
 */

public class RecipeRestClient {
    private static final String BASE_URL = "http://go.udacity.com/android-baking-app-json";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url,
                           RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
