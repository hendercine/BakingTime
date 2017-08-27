/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/7/17 1:05 PM
 */

package com.hendercine.android.bakinbuns.data;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.network.RecipeClient;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * BakinBuns created by hendercine on 8/7/17.
 */

public class DataFragment extends Fragment {

    private Observable<List<Recipe>> recipeModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setRetainInstance(true);

        FragmentManager fm = getFragmentManager();
        DataFragment df = (DataFragment) fm.findFragmentByTag("DataFragment");
        if(df == null) {
            fm.beginTransaction()
                    .add(new DataFragment(), "DataFragment")
            .commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public Observable<List<Recipe>> getRecipeModel() {
        if (recipeModel == null) {
            recipeModel = RecipeClient.getInstance()
                    .getRecipeFromJson()
                    .cache()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        return recipeModel;
    }

}
