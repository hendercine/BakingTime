/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/11/17 4:26 PM
 */

package com.hendercine.android.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.models.Ingredient;
import com.hendercine.android.bakingtime.ui.StepsListActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * BakingTime created by hendercine on 10/11/17.
 */

class RecipeWidgetRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeWidgetRemoteViewsFactory(this.getApplicationContext());
    }

    private class RecipeWidgetRemoteViewsFactory implements RemoteViewsFactory {

        private Context mContext;
        ArrayList<Ingredient> mIngredients;

        public RecipeWidgetRemoteViewsFactory(Context context) {
            this.mContext = context;

            mIngredients = new ArrayList<>();
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = preferences.getString(StepsListActivity
                    .SHARED_PREFS_KEY, "");
            if (!json.equals("")) {
                Gson gson = new Gson();
                mIngredients = gson.fromJson(json, new
                        TypeToken<ArrayList<Ingredient>>() {
                        }.getType());
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (mIngredients != null) {
                return mIngredients.size();
            } else return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {

            Ingredient ingredient = mIngredients.get(position);
            float quantity = ingredient.getIngredientQuantity();
            CharSequence quantityStr = NumberFormat.getInstance().format(quantity);
            CharSequence measure = ingredient.getIngredientMeasure();
            CharSequence ingredientName = ingredient.getIngredientName();

            RemoteViews rv = new RemoteViews(
                    mContext.getPackageName(), R.layout.recipe_widget_item);

            rv.setTextViewText(R.id.ingredient_quantity, quantityStr);
            if (quantity < 1.1) {
                rv.setTextViewText(R.id.ingredient_measure, measure);
            } else {
                rv.setTextViewText(R.id.ingredient_measure, measure + "s");
            }
            rv.setTextViewText(R.id.ingredient_name, ingredientName);
            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
