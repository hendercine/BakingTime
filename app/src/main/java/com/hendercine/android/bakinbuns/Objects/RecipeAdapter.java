/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 6:26 PM
 */

package com.hendercine.android.bakinbuns.Objects;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;

import java.util.List;

import butterknife.BindView;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    @BindView(R.id.main_grid_item_title)
    TextView recipeNameView;
    @BindView(R.id.main_grid_item_servings)
    TextView numberOfServingsView;
    @BindView(R.id.main_grid_item)
    CardView gridItemCard;

    public RecipeAdapter(@NonNull Context context, @NonNull List<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Recipe currentRecipe = getItem(position);

        assert currentRecipe != null;
        String recipeName = currentRecipe.getRecipeName();
        String numberOfServings = String.valueOf(currentRecipe.getServings());
        gridItemCard = (CardView) convertView;

        if (gridItemCard == null) {
            gridItemCard = (CardView) LayoutInflater.from(getContext())
                    .inflate(R.layout.main_rv_grid_item, parent, false);
        }

        recipeNameView.setText(recipeName);
        numberOfServingsView.setText(numberOfServings);
        return gridItemCard;
    }
}
