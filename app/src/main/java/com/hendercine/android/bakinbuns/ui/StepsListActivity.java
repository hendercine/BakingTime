package com.hendercine.android.bakinbuns.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.StepsRecyclerViewAdapter;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

///**
// * An activity representing a recipeList of Recipes. This activity
// * has different presentations for handset and tablet-size devices. On
// * handsets, the activity presents a recipeList of items, which when touched,
// * lead to a {@link StepsDetailActivity} representing
// * item details. On tablets, the activity presents the recipeList of items and
// * item details side-by-side using two vertical panes.
// */
public class StepsListActivity extends AppCompatActivity implements StepsRecyclerViewAdapter.OnItemClickListener {

//    @State(RecipeBundler.class)
    Recipe mRecipe;

//    @State(StepBundler.class)
    Step mStep;

//    @State(DetailFragmentBundler.class)
    StepsDetailFragment mStepsDetailFragment;

//    @State(StepListBundler.class)
    ArrayList<Step> mStepShortDescriptionList;

//    @State(StepListBundler.class)
    ArrayList<Step> mStepArrayList;

    @Nullable
    @BindView(R.id.recipe_detail_container)
    FrameLayout detailsContainerView;

    @Nullable
    @BindView(R.id.steps_list)
    RecyclerView stepsListView;

//    @State
    boolean mIsDualPane;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_steps_list);
        ButterKnife.bind(this);

        mIsDualPane = detailsContainerView != null &&
                detailsContainerView.getVisibility() == View.VISIBLE;

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra("recipe"));

        setTitle(mRecipe.getRecipeName());

        mStepArrayList = new ArrayList<>();
        mStepShortDescriptionList = new ArrayList<>();

        mStepArrayList = mRecipe.getStepList();
        if (mStepArrayList != null) {
            for (int i = 0; i < mStepArrayList.size(); i++) {
                mStep = new Step();
                mStep.setShortDescription(mStepArrayList.get(i).getShortDescription());
                mStep.setStepId(mStepArrayList.get(i).getStepId());
                mStep.setDescription(mStepArrayList.get(i).getDescription());
                mStep.setVideoURL(mStepArrayList.get(i).getVideoURL());
                mStep.setThumbnailURL(mStepArrayList.get(i).getThumbnailURL());

//                mIntent = new Intent(this, StepsDetailFragment.class);
//                mIntent.putExtra("step_details", Parcels.wrap(mStep));

                mStepShortDescriptionList.add(mStep);
            }
        }

        if (stepsListView != null) {
            stepsListView.setLayoutManager(new LinearLayoutManager
                    (StepsListActivity.this));
            StepsRecyclerViewAdapter adapter = new StepsRecyclerViewAdapter
                    (mStepShortDescriptionList);

            adapter.setClickListener(StepsListActivity.this);
            stepsListView.setAdapter(adapter);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onItemClick(Step step) {

//        ArrayList<Step> stepDetailList = new ArrayList<>();

//        stepDetailList.add(mStep);

        mStepsDetailFragment = new StepsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("step_details", Parcels.wrap(step));
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
        }

//        Intent intent = new Intent(StepsListActivity.this,
//                StepsDetailActivity.class);
//        intent.putExtra("steps", Parcels.wrap(mStep));

//        Bundle arguments = new Bundle();
//        arguments.putString(StepsDetailFragment.ARG_ITEM_ID,
//                getIntent().getStringExtra(StepsDetailFragment
//                        .ARG_ITEM_ID));
//        mStepsDetailFragment.setArguments(arguments);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.recipe_detail_container, mStepsDetailFragment)
//                .commit();
    }
}
