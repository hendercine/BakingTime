package com.hendercine.android.bakinbuns;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import icepick.Icepick;

public class MainSelectionActivity extends AppCompatActivity {

    // Create the LeakCanary watcher
    public static RefWatcher getRefWatcher(Context context) {
        MainSelectionActivity activity = (MainSelectionActivity) context.getApplicationContext();
        return activity.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Install the LeakCanary watcher
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(getApplication());

        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_main_selection);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.grid_view_recipe_cards, new MainSelectionFragment())
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    // TODO: Remove or uncomment and modify the following code.
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
