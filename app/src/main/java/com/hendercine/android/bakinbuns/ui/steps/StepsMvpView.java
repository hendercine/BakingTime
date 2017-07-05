/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 3:48 PM
 */

package com.hendercine.android.bakinbuns.ui.steps;

import com.hendercine.android.bakinbuns.data.db.model.Step;
import com.hendercine.android.bakinbuns.ui.base.MvpView;

import java.util.List;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public interface StepsMvpView extends MvpView {
    void updateStep(List<Step> stepList);
}
