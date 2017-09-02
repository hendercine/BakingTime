package com.hendercine.android.bakinbuns.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.adapters.StepsRecyclerViewAdapter;
import com.hendercine.android.bakinbuns.data.bundlers.DetailFragmentBundler;
import com.hendercine.android.bakinbuns.data.bundlers.RecipeListBundler;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

///**
// * An activity representing a recipeList of Recipes. This activity
// * has different presentations for handset and tablet-size devices. On
// * handsets, the activity presents a recipeList of items, which when touched,
// * lead to a {@link RecipeDetailActivity} representing
// * item details. On tablets, the activity presents the recipeList of items and
// * item details side-by-side using two vertical panes.
// */
public class RecipeStepsActivity extends AppCompatActivity implements StepsRecyclerViewAdapter.ItemClickListener {

    Recipe mRecipe;
    Step mStep;
    ArrayList<Step> mStepShortDescriptionList;
    @State(RecipeListBundler.class)
    ArrayList<Recipe> recipeList;

    @State(DetailFragmentBundler.class)
    StepsListFragment mStepsFragment;
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
        setContentView(R.layout.activity_recipe_steps);
        ButterKnife.bind(this);

        mIsDualPane = detailsContainerView != null &&
                detailsContainerView.getVisibility() == View.VISIBLE;


        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra("recipe"));
        mStepArrayList = mRecipe.getStepList();

        mStepShortDescriptionList = new ArrayList<>();

        if (mStepArrayList != null) {
            for (int i = 0; i < mStepArrayList.size(); i++) {
                mStep = new Step();
                mStep.setShortDescription(mStepArrayList.get(i).getShortDescription());
                mStepShortDescriptionList.add(mStep);
            }
        }

//        if (mIsDualPane) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.recipe_detail_container, mStepsFragment)
//                    .commit();
//        } else
//
//            mIsDualPane = false;
//
//        Bundle bundle = new Bundle();
//
//        final Intent intent = new Intent(this, StepsListFragment.class);
//        intent.putExtras(bundle);

        if (stepsListView != null) {
            mStepShortDescriptionList = new ArrayList<>();

            stepsListView.setLayoutManager(new LinearLayoutManager
                    (RecipeStepsActivity.this));
            StepsRecyclerViewAdapter adapter = new StepsRecyclerViewAdapter
                    (mStepShortDescriptionList);

            adapter.setClickListener(RecipeStepsActivity.this);
            stepsListView.setAdapter(adapter);
        }
}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onItemClick(View view, int position) {
//        mRecipeArrayList = Parcels.unwrap(getIntent().getParcelableExtra
//                ("recipe_list"));
//        Intent intent = new Intent(RecipeStepsActivity.this,
//                StepsDetailFragment.class);
//        intent.putExtra("recipe_step", Parcels.wrap(mRecipeArrayList));
    }
}
