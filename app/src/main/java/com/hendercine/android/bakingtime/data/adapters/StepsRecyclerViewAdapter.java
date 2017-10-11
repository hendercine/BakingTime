/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/7/17 10:07 AM
 */

package com.hendercine.android.bakingtime.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 8/7/17.
 */

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.StepsListViewHolder> {

    private ArrayList<Step> mStepList;
    private OnItemClickListener mClickListener;
    private int focusedItem = RecyclerView.NO_POSITION;

    public StepsRecyclerViewAdapter(ArrayList<Step> stepList) {
        mStepList = stepList;
    }

    @Override
    public StepsRecyclerViewAdapter.StepsListViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_item, parent, false);
        return new StepsRecyclerViewAdapter.StepsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            StepsRecyclerViewAdapter.StepsListViewHolder holder, final int position) {
        final Step step = getItem(position);
        holder.itemView.setSelected(focusedItem == position);
        holder.mStepListTextView.setText(step.getShortDescription());
        holder.mStepListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(step);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (mStepList == null) ? 0 : mStepList.size();
    }

    public Step getItem(int position) {
        if (position < 0 || position >= mStepList.size()) {
            return null;
        } else {
            return mStepList.get(position);
        }
    }

    public long getItemId(int position) {
        return position;
    }

    class StepsListViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        @BindView(R.id.step_description_btn)
        TextView mStepListTextView;

        StepsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                    notifyItemChanged(focusedItem);
                    focusedItem = getLayoutPosition();
                    notifyItemChanged(focusedItem);
                }
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Step step);
    }
}
