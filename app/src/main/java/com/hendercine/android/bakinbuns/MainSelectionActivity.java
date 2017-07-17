package com.hendercine.android.bakinbuns;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import timber.log.Timber;

public class MainSelectionActivity extends AppCompatActivity implements
        MainRecyclerViewGridAdapter.ItemClickListener {

    private RefWatcher refWatcher;
    private MainRecyclerViewGridAdapter mAdapter;
    @BindView(R.id.main_rv_recipe_cards)
    RecyclerView mainGridCards;


    // Create the LeakCanary watcher
    public static RefWatcher getRefWatcher(Context context) {
        MainSelectionActivity activity = (MainSelectionActivity) context.getApplicationContext();
        return activity.refWatcher;
    }

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
        ButterKnife.bind(this);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42", "43", "44", "45", "46", "47", "48"};

        int numberOfColumns = Utils.calculateNoOfColumns(getApplicationContext())
                ;
        mainGridCards.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        mAdapter = new MainRecyclerViewGridAdapter(this, data);
        mAdapter.setClickListener(this);
        mainGridCards.setAdapter(mAdapter);
        }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onItemClick(View view, int position) {
        Timber.i("TAG", "You clicked number "
                + mAdapter.getItem(position)
                + ", "
                + "which is at cell position: "
                + position);
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
