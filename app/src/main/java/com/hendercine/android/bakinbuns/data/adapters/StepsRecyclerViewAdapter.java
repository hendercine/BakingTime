/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/7/17 10:07 AM
 */

package com.hendercine.android.bakinbuns.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 8/7/17.
 */

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.StepsListViewHolder> {

    private List<Recipe> list;
    private StepsRecyclerViewAdapter.ItemClickListener mClickListener;

    public StepsRecyclerViewAdapter(List<Recipe> list) {
        this.list = list;
    }

    @Override
    public StepsRecyclerViewAdapter.StepsListViewHolder onCreateViewHolder
            (ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recipe_steps, parent, false);
        return new StepsRecyclerViewAdapter.StepsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsRecyclerViewAdapter.StepsListViewHolder holder, int
            position) {

        Recipe recipe = list.get(position);
        holder.mStepListTextView.setText(recipe.getShortDescription());

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

    class StepsListViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {


        @BindView(R.id.step_description)
        TextView mStepListTextView;

        StepsListViewHolder(View itemView) {
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

    public void setClickListener(StepsRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
