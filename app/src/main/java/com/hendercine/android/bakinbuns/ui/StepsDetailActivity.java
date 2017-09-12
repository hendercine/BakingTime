package com.hendercine.android.bakinbuns.ui;
//
//import android.content.Intent;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.hendercine.android.bakinbuns.R;

//import android.support.design.widget.Snackbar;
//import android.view.MenuItem;
//
///**
// * An activity representing a single Recipe detail screen. This
// * activity is only used narrow width devices. On tablet-size devices,
// * item details are presented side-by-side with a recipeList of items
// * in a {@link StepsListActivity}.
// */
public class StepsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {

            finish();
            return;
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            StepsDetailFragment details = new StepsDetailFragment();
            details.setArguments(getIntent().getBundleExtra("step_details"));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_portrait, details)
                    .commit();
        }
    }
}
