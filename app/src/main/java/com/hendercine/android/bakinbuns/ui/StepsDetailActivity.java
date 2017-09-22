/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 9/22/17 9:40 AM
 */

package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.bundlers.StepBundler;
import com.hendercine.android.bakinbuns.data.bundlers.StepListBundler;
import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import java.util.ArrayList;

import icepick.Icepick;
import icepick.State;

/**
 * BakinBuns created by hendercine on 9/22/17.
 */

public class StepsDetailActivity extends AppCompatActivity {

    @State(StepBundler.class) Step mStep;
    @State(StepListBundler.class) ArrayList<Step> mStepsList;

    private Boolean mIsDualPane;
    private int step_index;
    private ArrayList<Step> mStepDetailsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        setContentView(R.layout.activity_step_detail);

        mStepDetailsList = Parcels.unwrap(getIntent().getParcelableExtra("steps_list"));
        step_index = getIntent().getIntExtra("step_index", 0);


    }
}
