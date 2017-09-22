package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.StepsRecyclerViewAdapter;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeBundler;
import com.hendercine.android.bakinbuns.data.bundlers.StepBundler;
import com.hendercine.android.bakinbuns.data.bundlers.StepListBundler;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

/**
 * // * An activity representing a recipeList of Recipes. This activity
 * // * has different presentations for handset and tablet-size devices. On
 * // * handsets, the activity presents a recipeList of items, which when touched,
 * // * lead to a {@link StepsDetailFragment} representing
 * // * item details. On tablets, the activity presents the recipeList of items and
 * // * item details side-by-side using two vertical panes.
 * //
 */
public class StepsListActivity extends AppCompatActivity
        implements StepsRecyclerViewAdapter.OnItemClickListener {

    @State(RecipeBundler.class)
    Recipe mRecipe;

    @State(StepBundler.class)
    Step mStep;

    //    @State(DetailFragmentBundler.class)
    StepsDetailFragment mStepsDetailFragment;

    @State(StepListBundler.class)
    ArrayList<Step> mStepDetailsList;

    @State(StepListBundler.class)
    ArrayList<Step> mStepArrayList;

    @Nullable
    @BindView(R.id.recipe_detail_container)
    FrameLayout detailsContainerView;

    @Nullable
    @BindView(R.id.steps_list)
    RecyclerView stepsListView;

    @State
    boolean mIsDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_steps_list);
        ButterKnife.bind(this);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mIsDualPane = detailsContainerView != null &&
                detailsContainerView.getVisibility() == View.VISIBLE;

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra("recipe"));

        setTitle(mRecipe.getRecipeName());

        mStepArrayList = new ArrayList<>();
        mStepDetailsList = new ArrayList<>();

        mStepArrayList = mRecipe.getStepList();
        if (mStepArrayList != null) {
            for (int i = 0; i < mStepArrayList.size(); i++) {
                mStep = new Step();
                mStep.setShortDescription(mStepArrayList.get(i).getShortDescription());
                mStep.setStepId(mStepArrayList.get(i).getStepId());
                mStep.setDescription(mStepArrayList.get(i).getDescription());
                mStep.setVideoURL(mStepArrayList.get(i).getVideoURL());
                mStep.setThumbnailURL(mStepArrayList.get(i).getThumbnailURL());

                mStepDetailsList.add(mStep);
            }
        }

//        Intent intent = new Intent(
//                StepsListActivity.this,
//                StepsDetailFragment.class);
//        intent.putExtra("steps_list", Parcels.wrap(mStepDetailsList));

        if (stepsListView != null) {
            stepsListView.setLayoutManager(new LinearLayoutManager
                    (StepsListActivity.this));
            StepsRecyclerViewAdapter adapter = new StepsRecyclerViewAdapter
                    (mStepDetailsList);

            adapter.setClickListener(StepsListActivity.this);
            stepsListView.setAdapter(adapter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onItemClick(ArrayList<Step> stepArrayList) {

        mStepsDetailFragment = new StepsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("step_list", Parcels.wrap(stepArrayList));
        mStepsDetailFragment.setArguments(bundle);

        if (mIsDualPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipe_detail_container, mStepsDetailFragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.step_frame, mStepsDetailFragment)
                    .commit();
            findViewById(R.id.steps_list_layout).setVisibility(View.GONE);
        }

    }
}
