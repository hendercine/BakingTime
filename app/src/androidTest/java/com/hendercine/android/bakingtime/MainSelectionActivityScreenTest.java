/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 9/21/17 3:32 PM
 */

package com.hendercine.android.bakingtime;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hendercine.android.bakingtime.ui.MainSelectionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * BakinBuns created by hendercine on 9/21/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainSelectionActivityScreenTest {

    private static final String INGREDIENTS_BTN = "Recipe Ingredients";

    @Rule
    public ActivityTestRule<MainSelectionActivity> mActivityTestRule =
            new ActivityTestRule<>(MainSelectionActivity.class);

    /**
     * Clicks on a RecyclerViewGrid item and checks it opens up the
     * StepsListActivity with the correct details.
     */
    @Test
    public void clickRecyclerViewGridItem_OpensStepsListActivity() {

        onView(withId(R.id.hand_held_rv_recipe_cards)).perform
                (RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.ingredients_btn)).check(matches(withText
                (INGREDIENTS_BTN)));
    }
}
