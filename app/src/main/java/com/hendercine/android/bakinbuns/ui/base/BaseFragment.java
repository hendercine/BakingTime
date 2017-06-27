/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 10:51 AM
 */

package com.hendercine.android.bakinbuns.ui.base;

import android.app.Fragment;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached();
    }
}
