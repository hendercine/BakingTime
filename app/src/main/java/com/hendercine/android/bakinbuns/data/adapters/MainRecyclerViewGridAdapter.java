/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/21/17 4:33 PM
 */

package com.hendercine.android.bakinbuns.data.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class MainRecyclerViewGridAdapter extends RecyclerView
        .Adapter<MainRecyclerViewGridAdapter.MainGridViewHolder> {

    private ArrayList<Recipe> recipeArrayList = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MainRecyclerViewGridAdapter(Context context, ArrayList<Recipe> arrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.recipeArrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public Recipe getItem(int position) {
        if (position < 0 || position >= recipeArrayList.size()) {
            return null;
        } else {
            return recipeArrayList.get(position);
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public void setRecipeArrayList(ArrayList<Recipe> recipes) {
        if (recipes == null) {
            return;
        }
        recipeArrayList.clear();
        recipeArrayList.addAll(recipes);
        notifyDataSetChanged();
    }

    @Override
    public MainGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.main_rv_grid_item, parent, false);
        return new MainGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainGridViewHolder holder, int position) {

        Recipe recipeIndex = recipeArrayList.get(position);
        String recipeName = recipeIndex.recipeName;
        String servings = String.valueOf(recipeIndex.servings);
        holder.setRecipeName(recipeName);
        holder.setServings(servings);
    }

    class MainGridViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.main_grid_item)
        CardView mMainCardView;

        @BindView(R.id.main_grid_item_servings) TextView mServingsTextView;

        @BindView(R.id.main_grid_item_title) TextView mTitleTextView;

        MainGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setRecipeName(String recipeName) {
            mTitleTextView.setText(recipeName);

        }

        public void setServings(String servings) {
            mServingsTextView.setText(servings);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
