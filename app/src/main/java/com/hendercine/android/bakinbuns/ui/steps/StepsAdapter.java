/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 7/3/17 1:46 PM
 */

package com.hendercine.android.bakinbuns.ui.steps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.db.model.Step;
import com.hendercine.android.bakinbuns.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * BakinBuns created by hendercine on 7/3/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<Step> mStepResponseList;

    public StepsAdapter(List<Step> stepResponseList) {
        mStepResponseList = stepResponseList;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.recipe_steps, parent, false));
            case VIEW_TYPE_EMPTY:
                default:
                    return new EmptyViewHolder(
                            LayoutInflater.from(parent.getContext()).inflate(R.layout
                                    .item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mStepResponseList != null && mStepResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mStepResponseList != null && mStepResponseList.size() > 0) {
            return mStepResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Step> stepList) {
        mStepResponseList.addAll(stepList);
        notifyDataSetChanged();
    }

    private interface Callback {
        void onStepsEmptyViewRetryClick();
    }

    private class ViewHolder extends BaseViewHolder {

        @BindView(R.id.step_title)
        TextView stepTitleView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            stepTitleView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Step step = mStepResponseList.get(position);

            if (step.getShortDescription() != null) {
                stepTitleView.setText(step.getShortDescription());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (step.getShortDescription() != null) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(step.getShortDescription()));
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onStepsEmptyViewRetryClick();
        }
    }
}
