/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 10/17/17 3:12 PM
 */

package com.hendercine.android.bakingtime;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hendercine.android.bakingtime.ui.StepsListActivity;
import com.hendercine.android.bakingtime.utils.StepsListActivityIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * BakingTime created by hendercine on 10/17/17.
 */

@RunWith(AndroidJUnit4.class)
public class StepsListActivityScreenTest {

    private StepsListActivityIdlingResource idlingResource;

    @Rule
    public ActivityTestRule<StepsListActivity> mActivityTestRule =
            new ActivityTestRule<>(StepsListActivity.class);

    @Before
    public void registerStepsIdlingResource() {
        StepsListActivity activity = mActivityTestRule.getActivity();
        idlingResource = new StepsListActivityIdlingResource(activity);
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterStepsIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    /**
     * Clicks on a RecyclerViewGrid item and checks it opens up the
     * StepsListActivity with the correct details.
     */
    @Test
    public void clickRecyclerViewGridItem_OpensStepsListActivity() {

        onView(withId(R.id.ingredients_btn)).perform
                (click());
        onView(withId(R.id.ingredient_list)).check((ViewAssertion) isDisplayed());
    }
}
