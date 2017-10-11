/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/21/17 4:33 PM
 */

package com.hendercine.android.bakingtime.data.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class MainRecyclerViewGridAdapter extends RecyclerView
        .Adapter<MainRecyclerViewGridAdapter.MainGridViewHolder> {

    private ArrayList<Recipe> list;
    private ItemClickListener mClickListener;

    public MainRecyclerViewGridAdapter(ArrayList<Recipe> list) {
        this.list = list;
    }

    @Override
    public MainGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_rv_grid_item, parent, false);
        return new MainGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainGridViewHolder holder, int position) {

        Recipe recipe = list.get(position);
        holder.mTitleTextView.setText(recipe.getRecipeName());
        holder.mServingsTextView.setText(String.valueOf(recipe.getServings()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Recipe getItem(int position) {
        if (position < 0 || position >= list.size()) {
            return null;
        } else {
            return list.get(position);
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public void setList(ArrayList<Recipe> recipes) {
        if (recipes == null) {
            return;
        }
        list.clear();
        list.addAll(recipes);
        notifyDataSetChanged();
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
