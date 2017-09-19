/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/20/17 10:00 PM
 */

package com.hendercine.android.bakinbuns.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;

import com.hendercine.android.bakinbuns.data.models.Ingredient;
import com.hendercine.android.bakinbuns.data.models.Step;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class Utils {

    public static final ButterKnife.Action<RecyclerView> DISABLE = new ButterKnife
            .Action<RecyclerView>() {
        @Override
        public void apply(RecyclerView view, int index) {
            view.setEnabled(false);
        }
    };

    public static final ButterKnife.Setter<RecyclerView, Boolean> ENABLED =
            new ButterKnife.Setter<RecyclerView, Boolean>() {
                @Override
                public void set(RecyclerView view, Boolean value, int index) {
                    view.setEnabled(value);
                }
            };

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public ArrayList<Ingredient> getIngredientData(ArrayList<Ingredient> ingredients) {

        Ingredient ingredient;
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        for (int i = 0; i < ingredients.size(); ++i) {
            ingredient = new Ingredient();
            ingredient.setIngredientName(ingredients.get(i).getIngredientName());
            ingredient.setIngredientQuantity(ingredients.get(i).getIngredientQuantity());
            ingredient.setIngredientMeasure(ingredients.get(i).getIngredientMeasure());

            ingredientsList.add(ingredient);
        }

        return ingredientsList;
    }

    public ArrayList<Step> getStepData(ArrayList<Step> steps) {

        Step step;
        ArrayList<Step> stepsList = new ArrayList<>();

        for (int i = 0; i < steps.size(); ++i) {
            step = new Step();
            step.setStepId(steps.get(i).getStepId());
            step.setShortDescription(steps.get(i).getShortDescription());
            step.setDescription(steps.get(i).getDescription());
            step.setVideoURL(steps.get(i).getVideoURL());
            step.setThumbnailURL(steps.get(i).getThumbnailURL());

            stepsList.add(step);
        }

        return stepsList;
    }

}
