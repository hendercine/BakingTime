package com.hendercine.android.bakingtime.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.adapters.StepsRecyclerViewAdapter;
import com.hendercine.android.bakingtime.data.bundlers.IngredientBundler;
import com.hendercine.android.bakingtime.data.bundlers.IngredientListBundler;
import com.hendercine.android.bakingtime.data.bundlers.RecipeBundler;
import com.hendercine.android.bakingtime.data.bundlers.StepBundler;
import com.hendercine.android.bakingtime.data.bundlers.StepListBundler;
import com.hendercine.android.bakingtime.data.models.Ingredient;
import com.hendercine.android.bakingtime.data.models.Recipe;
import com.hendercine.android.bakingtime.data.models.Step;
import com.hendercine.android.bakingtime.ui.widget.RecipeWidgetProvider;
import com.hendercine.android.bakingtime.utils.RemoveFragmentListener;
import com.hendercine.android.bakingtime.utils.StepsListActivityIdlingResource;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.Icepick;
import icepick.State;

import static com.hendercine.android.bakingtime.ui.MainSelectionActivity.ProgressListener;

/**
 * // * An activity representing a recipeList of Recipes. This activity
 * // * has different presentations for handset and tablet-size devices. On
 * // * handsets, the activity presents a recipeList of items, which when touched,
 * // * lead to a {@link StepsDetailFragment} or a {@link IngredientFragment} representing
 * // * recipe details. On tablets, the activity presents the recipeList of
 * // * recipe andrecipe details side-by-side using two vertical panes.
 */
public class StepsListActivity extends AppCompatActivity
        implements StepsRecyclerViewAdapter.OnItemClickListener, RemoveFragmentListener {

    private static final String TAG = StepsListActivity.class.getSimpleName();
    public static final String SHARED_PREFS_KEY = "SHARED_PREFS_KEY";
    private StepsDetailFragment mStepsDetailFragment;
    private IngredientFragment mIngredientFragment;
    private Step mSelectedStep;
    private ProgressListener mListener;
    private MenuItem mActionProgressItem;

    @Nullable
    StepsListActivityIdlingResource mIdlingResource;

    @Nullable
    @BindView(R.id.recipe_detail_container)
    FrameLayout detailsContainerView;

    @BindView(R.id.steps_list_layout)
    LinearLayout stepListLayout;

    @Nullable
    @BindView(R.id.steps_list)
    RecyclerView stepsListView;

    @Nullable
    @BindView(R.id.step_detail_layout)
    RecyclerView stepDetailView;

    @Nullable
    @BindView(R.id.ingredient_list)
    RecyclerView ingredientsListView;

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
    private ActionBar actionBar;
    private String mRecipeName;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new StepsListActivityIdlingResource(this);
        }
        return mIdlingResource;
    }

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
        mRecipeName = mRecipe.getRecipeName();

        // Set the title and show the Up button in the action bar.
        if (mRecipeName != null) {
            setTitle(mRecipeName);
        }
        actionBar = getSupportActionBar();
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

        mIngredientFragment = new IngredientFragment();
        if (mIsDualPane && savedInstanceState == null) {
            if (mStepsDetailFragment == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.recipe_detail_container,
                                mIngredientFragment.newInstance(mRecipe))
                        .commit();
            }
        }

        getIdlingResource();
        makeData();
        sendBroadcast();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        int stepIndex = mStepDetailsList.indexOf(mSelectedStep);
        outState.putInt("step_index", stepIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mActionProgressItem = menu.findItem(R.id.menu_action_progress);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (!mIsDualPane && ingredientsListView != null) {
                stepListLayout.setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.ingredients_btn)
    public void onClick() {
        showProgressBar();
        if (mIsDualPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipe_detail_container,
                            mIngredientFragment.newInstance(mRecipe))
                    .addToBackStack(null)
                    .commit();
            hideProgressBar();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.step_frame,
                            mIngredientFragment.newInstance(mRecipe),
                            "INGREDIENTS_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
            hideProgressBar();
            actionBar.hide();
            stepListLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(Step selectedStep) {
        this.mSelectedStep = selectedStep;
        int stepIndex = mStepDetailsList.indexOf(mSelectedStep);
        Bundle extras = new Bundle();
        extras.putString("recipe_name", mRecipeName);
        extras.putParcelable("selected_step", Parcels.wrap(selectedStep));
        extras.putParcelable("steps_list", Parcels.wrap(mStepDetailsList));
        extras.putInt("step_index", stepIndex);
        mStepsDetailFragment = new StepsDetailFragment();
        mStepsDetailFragment.setArguments(extras);

        if (mIsDualPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipe_detail_container, mStepsDetailFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.step_frame, mStepsDetailFragment, "DETAIL_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
            actionBar.hide();
            stepListLayout.setVisibility(View.GONE);
        }
    }

    public void onRemoveFragment(String tag) {
        getSupportFragmentManager().findFragmentByTag(tag);
        getSupportFragmentManager().popBackStack();
        stepListLayout.setVisibility(View.VISIBLE);
        actionBar.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (stepDetailView != null || ingredientsListView != null) {
            stepListLayout.setVisibility(View.VISIBLE);
            actionBar.show();
            onResume();
        }
    }

    private void makeData() {
        ArrayList<Ingredient> ingredientsForWidget = new ArrayList<>();
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

                ingredientsForWidget.add(mIngredients);
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(ingredientsForWidget);

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SHARED_PREFS_KEY, json).apply();
    }

    private void sendBroadcast() {
        Intent intent = new Intent(this, RecipeWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        sendBroadcast(intent);
    }

    public void setProgressListener(ProgressListener progressListener) {
        mListener = progressListener;
    }

    private void showProgressBar() {
        // show the progress and notify the listener
        mActionProgressItem.setVisible(true);
        notifyListener(mListener);
    }

    private void hideProgressBar() {
        // hide the progress and notify the listener
        mActionProgressItem.setVisible(false);
        notifyListener(mListener);
    }

    public boolean isInProgress() {
        // return true if progress is visible
        boolean progressVisible = false;
        if (mActionProgressItem.isVisible()) {
            progressVisible = true;
        }
        return progressVisible;
    }

    private void notifyListener(ProgressListener listener) {
        if (listener == null) {
            return;
        }
        if (isInProgress()) {
            listener.onProgressShown();
        } else {
            listener.onProgressHidden();
        }
    }

}
