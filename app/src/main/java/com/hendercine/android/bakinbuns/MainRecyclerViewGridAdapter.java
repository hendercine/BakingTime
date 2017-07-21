/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 3:34 PM
 */

package com.hendercine.android.bakinbuns;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

class MainRecyclerViewGridAdapter extends RecyclerView
        .Adapter<MainRecyclerViewGridAdapter.ViewHolder> {

//    TODO: Change ArrayList<String> to ArrayList<Recipe>

    private ArrayList<String> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    MainRecyclerViewGridAdapter(Context context, ArrayList<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.main_rv_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewGridAdapter.ViewHolder holder, int position) {
        String recipeName = mData.get(position);
        String recipeServings = mData.get(position);
        if (holder != null) {
            holder.mMainCardView.setClickable(true);
            holder.mTitleTextView.setText(recipeName);
            holder.mServingsTextView.setText(recipeServings);

            // TODO: Uncomment below after setting up JSON Fetch Task
            // String recipe = mData.get(position);
            // holder.mTitleTextView.setText(recipe);
            // holder.mServingsTextView.setText(recipe);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.main_grid_item)
        CardView mMainCardView;

        @BindView(R.id.main_grid_item_servings) TextView mServingsTextView;

        @BindView(R.id.main_grid_item_title) TextView mTitleTextView;

        ViewHolder(View itemView) {
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

        String getItem(int id) {
            return String.valueOf(mData.get(id));
        }

        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
