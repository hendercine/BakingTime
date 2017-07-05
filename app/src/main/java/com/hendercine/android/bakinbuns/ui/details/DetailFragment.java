/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 3:45 PM
 */

package com.hendercine.android.bakinbuns.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.di.component.ActivityComponent;
import com.hendercine.android.bakinbuns.ui.base.BaseFragment;
import com.hendercine.android.bakinbuns.ui.steps.StepsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An activity representing a single Recipe detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StepsActivity}.
 */
public class DetailFragment extends BaseFragment implements DetailsMvpView  {

    public static final String TAG = "DetailFragment";

    @Inject
    DetailsMvpPresenter<DetailsMvpView> mPresenter;

    @BindView(R.id.vid_view)
    VideoView mVidView;

    @BindView(R.id.detail_step_instruction)
    CardView mStepInstructionCv;

    @BindView(R.id.step_instruction_text)
    TextView mStepInstructionTv;

    public DetailFragment newInstance() {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View detailView = inflater.inflate(R.layout.fragment_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnbinder(ButterKnife.bind(this, detailView));
            mPresenter.onAttach(this);
        }

        return detailView;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @OnClick(R.id.toolbar)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

//    @Override
//    public void updateStep(List<Step> stepList) {
//
//    }
}
