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

//    public static ArrayList<Recipe> getRecipeDataFromJson(String recipeJsonStr)
//            throws JSONException {
//
//        final String RECIPE_ID = "id";
//        final String RECIPE_NAME = "name";
//        final String RECIPE_INGREDIENTS = "ingredients";
//        final String INGREDIENT_QUANTITY = "quantity";
//        final String INGREDIENT_MEASURE = "measure";
//        final String INGREDIENT_NAME = "ingredient";
//        final String RECIPE_STEPS = "steps";
//        final String STEP_SHORT_DESCRIPTION = "shortDescription";
//        final String STEP_DESCRIPTION = "description";
//        final String STEP_VIDEO_URL = "videoURL";
//        final String STEP_THUMB_URL = "thumbnailURL";
//        final String RECIPE_SERVINGS = "servings";
//
//        JSONObject recipesJson = new JSONObject(recipeJsonStr);
//        JSONArray recipeArray = recipesJson.getJSONArray(RECIPE_ID);
//
//        ArrayList<Recipe> recipeResults = new ArrayList<>();
//
//        for (int i = 0; i < recipeArray.length(); i++) {
//            int recipeId;
//            String name;
//            int quantity;
//            String measure;
//            String ingredient;
//            String shortDescription;
//            String description;
//            String videoURL;
//            String thumbnailUrl;
//            int servings;
//
//            JSONObject currentRecipe = recipeArray.getJSONObject(i);
//            JSONArray currentIngredientArray = currentRecipe
//                    .getJSONArray(RECIPE_INGREDIENTS);
//            JSONObject currentIngredients = currentIngredientArray
//                    .getJSONObject(i);
//            JSONArray currentStepsArray = currentRecipe
//                    .getJSONArray(RECIPE_STEPS);
//            JSONObject currentSteps = currentStepsArray
//                    .getJSONObject(i);
//
//            recipeId = currentRecipe.getInt(RECIPE_ID);
//            name = currentRecipe.getString(RECIPE_NAME);
//            quantity = Integer.parseInt(currentIngredients.getString(INGREDIENT_QUANTITY));
//            measure = currentIngredients.getString(INGREDIENT_MEASURE);
//            ingredient = currentIngredients.getString(INGREDIENT_NAME);
//            shortDescription = currentSteps.getString(STEP_SHORT_DESCRIPTION);
//            description = currentSteps.getString(STEP_DESCRIPTION);
//            videoURL = currentSteps.getString(STEP_VIDEO_URL);
//            thumbnailUrl = currentSteps.getString(STEP_THUMB_URL);
//            servings = Integer.parseInt(currentRecipe.getString(RECIPE_SERVINGS));
//
//            Recipe recipe =
//                    new Recipe(recipeId, name, quantity, measure, ingredient,
//                            shortDescription, description, videoURL,
//                            thumbnailUrl, servings);
//            recipeResults.add(recipe);
//        }
//        return recipeResults;
//    }
}
