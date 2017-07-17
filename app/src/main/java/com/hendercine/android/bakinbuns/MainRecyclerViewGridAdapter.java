/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/10/17 3:34 PM
 */

package com.hendercine.android.bakinbuns;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

class MainRecyclerViewGridAdapter extends RecyclerView
        .Adapter<MainRecyclerViewGridAdapter.ViewHolder> {

    private LayoutInflater mInflater;
//    TODO: Change String[] to ArrayList<Recipe> and change other instances
//    to array access.
    private String[] mData = new String[0];
    private ItemClickListener mClickListener;

    MainRecyclerViewGridAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.activity_main_selection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewGridAdapter.ViewHolder holder, int position) {
        String recipe = mData[position];
        holder.mTitleTextView.setText(recipe);
        holder.mServingsTextView.setText(recipe);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.main_grid_item_servings)
        TextView mServingsTextView;

        @BindView(R.id.main_grid_item_title)
        TextView mTitleTextView;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

        String getItem(int id) {
            return String.valueOf(mData[id]);
        }

        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
