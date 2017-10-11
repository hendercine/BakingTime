/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 9/21/17 3:32 PM
 */

package com.hendercine.android.bakingtime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hendercine.android.bakingtime.ui.MainSelectionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * BakinBuns created by hendercine on 9/21/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainSelectionActivityIntentTest {
    @Rule
    public ActivityTestRule<MainSelectionActivity> mActivityTestRule =
            new ActivityTestRule<>(MainSelectionActivity.class);

    // Check for intent activation

    @Test
    public void MainActivityTest() {

//        SystemClock.sleep(1000);
//
//        onView(withId(R.id.hand_held_rv_recipe_cards))
//                .perform(actionOnItemAtPosition(1, click()));
//
//        onView(withId(R.id.recipeTest))
//                .check(matches(withText("Recipe")));
    }
}
