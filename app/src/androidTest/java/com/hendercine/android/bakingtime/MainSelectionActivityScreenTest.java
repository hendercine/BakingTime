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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * BakinBuns created by hendercine on 9/21/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainSelectionActivityScreenTest {

    private static final String RECIPE_NAME = "Nutella Pie";

    @Rule
    public ActivityTestRule<MainSelectionActivity> mActivityTestRule =
            new ActivityTestRule<>(MainSelectionActivity.class);

    /**
     * Clicks on a GridView item and checks it opens up the StepsListActivity
     * with the correct details.
     */
    @Test
    public void clickGridViewItem_OpensStepsListActivity() {

        onData(anything()).inAdapterView(withId(R.id
                .hand_held_rv_recipe_cards)).atPosition(1).perform(click());

        onView(withId(R.id.steps_list_layout)).check(matches(withText
                (RECIPE_NAME)));
    }
}
