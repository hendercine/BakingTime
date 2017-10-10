/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/5/17 4:13 PM
 */

package com.hendercine.android.bakinbuns.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Ingredient;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientRVAdapter extends RecyclerView.Adapter<IngredientRVAdapter.IngredientViewHolder> {

    private final ArrayList<Ingredient> mIngredientList;

    public IngredientRVAdapter(ArrayList<Ingredient> ingredients) {
        mIngredientList = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IngredientViewHolder holder, int position) {
        final Ingredient ingredient = getItem(position);
        if (ingredient != null) {
            float measureQuantity = ingredient.getIngredientQuantity();
            String quantityStr = NumberFormat.getInstance().format(measureQuantity);

            holder.ingredientQuantityView.setText(quantityStr);
            if (measureQuantity < 1.1) {
                holder.ingredientMeasureView.setText(ingredient.getIngredientMeasure());
            } else {
                holder.ingredientMeasureView.setText(
                        ingredient.getIngredientMeasure().concat("s"));
            }
            holder.ingredientNameView.setText(ingredient.getIngredientName());
        }
    }

    private Ingredient getItem(int position) {
        if (position < 0 || position >= mIngredientList.size()) {
            return null;
        } else {
            return mIngredientList.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingredient_quantity)
        TextView ingredientQuantityView;

        @BindView(R.id.ingredient_measure)
        TextView ingredientMeasureView;

        @BindView(R.id.ingredient_name)
        TextView ingredientNameView;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
