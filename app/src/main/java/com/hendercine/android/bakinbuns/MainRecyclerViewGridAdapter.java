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

import com.hendercine.android.bakinbuns.Objects.Recipe;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * BakinBuns created by hendercine on 7/10/17.
 */

public class MainRecyclerViewGridAdapter extends RecyclerView
        .Adapter<MainRecyclerViewGridAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Recipe> mData = new ArrayList<>();
    private ItemClickListener mClickListener;

    public MainRecyclerViewGridAdapter(Context context, ArrayList<Recipe> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.fragment_main_selection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewGridAdapter.ViewHolder holder, int position) {
        Recipe recipe = mData.get(position);
        holder.mCardTextView.setText((CharSequence) recipe);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.main_grid_item_text)
        public TextView mCardTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }
    }

        public String getItem(int id) {
            return String.valueOf(mData.get(id));
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
