package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.StepsRecyclerViewAdapter;
import com.hendercine.android.bakinbuns.data.bundlers.IngredientBundler;
import com.hendercine.android.bakinbuns.data.bundlers.IngredientListBundler;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeBundler;
import com.hendercine.android.bakinbuns.data.bundlers.StepBundler;
import com.hendercine.android.bakinbuns.data.bundlers.StepListBundler;
import com.hendercine.android.bakinbuns.data.models.Ingredient;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

/**
 * // * An activity representing a recipeList of Recipes. This activity
 * // * has different presentations for handset and tablet-size devices. On
 * // * handsets, the activity presents a recipeList of items, which when touched,
 * // * lead to a {@link StepsDetailFragment} or a {@link IngredientFragment} representing
 * // * recipe details. On tablets, the activity presents the recipeList of
 * // * recipe andrecipe details side-by-side using two vertical panes.
 */
public class StepsListActivity extends AppCompatActivity
        implements StepsRecyclerViewAdapter.OnItemClickListener {

    private static final String TAG = StepsListActivity.class.getSimpleName();
    private StepsDetailFragment mStepsDetailFragment;
    private IngredientFragment mIngredientFragment;
    private Step mSelectedStep;

    @Nullable
    @BindView(R.id.recipe_detail_container)
    FrameLayout detailsContainerView;

    @Nullable
    @BindView(R.id.steps_list)
    RecyclerView stepsListView;

    @Nullable
    @BindView(R.id.ingredients_btn)
    TextView ingredientsBtnView;

    @State(RecipeBundler.class)
    Recipe mRecipe;

    @State(StepBundler.class)
    Step mStep;

    @State(IngredientBundler.class)
    Ingredient mIngredients;

    @State(StepListBundler.class)
    ArrayList<Step> mStepDetailsList;

    @State(StepListBundler.class)
    ArrayList<Step> mStepArrayList;

    @State(IngredientListBundler.class)
    ArrayList<Ingredient> mIngredientArrayList;

    @State
    boolean mIsDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_steps_list);
        ButterKnife.bind(this);

        // Check if the Activity is in dual pane mode.
        mIsDualPane = detailsContainerView != null &&
                detailsContainerView.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mStepsDetailFragment =
                    (StepsDetailFragment) getSupportFragmentManager()
                            .getFragment(savedInstanceState, TAG);

        }

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra("recipe"));

        // Set the title and show the Up button in the action bar.
        setTitle(mRecipe.getRecipeName());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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

        if (stepsListView != null) {
            stepsListView.setLayoutManager(new LinearLayoutManager
                    (StepsListActivity.this));
            StepsRecyclerViewAdapter adapter = new StepsRecyclerViewAdapter
                    (mStepDetailsList);

            adapter.setClickListener(StepsListActivity.this);
            stepsListView.setAdapter(adapter);
        }

        mIngredientArrayList = new ArrayList<>();
        mIngredientArrayList = mRecipe.getIngredientList();
        if (mIngredientArrayList != null) {
            for (int i = 0; i < mIngredientArrayList.size(); i++) {
                mIngredients = new Ingredient();
                mIngredients.setIngredientName(
                        mIngredientArrayList.get(i).getIngredientName());
                mIngredients.setIngredientQuantity(
                        mIngredientArrayList.get(i).getIngredientQuantity());
                mIngredients.setIngredientMeasure(
                        mIngredientArrayList.get(i).getIngredientMeasure());
            }
        }
        if (mIsDualPane && savedInstanceState == null) {
            if (mStepsDetailFragment == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.recipe_detail_container,
                                IngredientFragment.newInstance(mRecipe))
                        .commit();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        int stepIndex = mStepDetailsList.indexOf(mSelectedStep);
        outState.putParcelable("selected_step", Parcels.wrap(mSelectedStep));
        outState.putParcelable("steps_list", Parcels.wrap(mStepDetailsList));
        outState.putInt("step_index", stepIndex);
        if (mStepsDetailFragment != null) {
            getSupportFragmentManager()
                    .putFragment(outState, TAG, mStepsDetailFragment);
        }
        if (mIngredientFragment != null) {
            getSupportFragmentManager()
                    .putFragment(outState, TAG, mIngredientFragment);
        }
    }

    @OnClick(R.id.ingredients_btn)
    public void onClick() {
        if (mIsDualPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipe_detail_container,
                            IngredientFragment.newInstance(mRecipe))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.step_frame, mStepsDetailFragment)
                    .commit();
            findViewById(R.id.steps_list_layout).setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(Step selectedStep) {
        this.mSelectedStep = selectedStep;
        int stepIndex = mStepDetailsList.indexOf(mSelectedStep);
        Bundle extras = new Bundle();
        extras.putParcelable("selected_step", Parcels.wrap(selectedStep));
        extras.putParcelable("steps_list", Parcels.wrap(mStepDetailsList));
        extras.putInt("step_index", stepIndex);
        mStepsDetailFragment = new StepsDetailFragment();
        mStepsDetailFragment.setArguments(extras);

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
