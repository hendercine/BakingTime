/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/7/17 10:07 AM
 */

package com.hendercine.android.bakinbuns.data.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BakinBuns created by hendercine on 8/7/17.
 */

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.StepsListViewHolder> {

    private List<Step> mStepList;
    Context mContext;
    private StepsRecyclerViewAdapter.ItemClickListener mClickListener;

    public StepsRecyclerViewAdapter(List<Step> stepList) {
        mStepList = stepList;
//        mContext = context;
    }

    @Override
    public StepsRecyclerViewAdapter.StepsListViewHolder onCreateViewHolder
            (ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_step_list_item, parent, false);
        return new StepsRecyclerViewAdapter.StepsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsRecyclerViewAdapter.StepsListViewHolder holder, int
            position) {

        Step step = mStepList.get(position);
        holder.mStepListTextView.setText(step.getShortDescription());

    }

    @Override
    public int getItemCount() {
        return mStepList.size();
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

    public void setStepList(ArrayList<Step> steps) {
        if (steps == null) {
            return;
        }
        mStepList.clear();
        mStepList.addAll(steps);
        notifyDataSetChanged();
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
