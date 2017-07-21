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

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

class MainRecyclerViewGridAdapter extends RecyclerView
        .Adapter<MainRecyclerViewGridAdapter.ViewHolder> {

    private LayoutInflater mInflater;
//    TODO: Change ArrayList<String> to ArrayList<Recipe>

    private ArrayList<String> mData = new ArrayList<>(Arrays.asList("1", "2",
        "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
        "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
        "40", "41", "42", "43", "44", "45", "46", "47", "48"));
    private ItemClickListener mClickListener;

    MainRecyclerViewGridAdapter(Context context, ArrayList<String> data) {
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
        if (holder != null) {
            holder.mTitleTextView.setText("Disgusting Hardcode");
            holder.mServingsTextView.setText("0 Servings");

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
            return String.valueOf(mData.get(id));
        }

        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
