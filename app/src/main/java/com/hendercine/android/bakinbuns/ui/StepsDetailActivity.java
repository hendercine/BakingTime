/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 9/22/17 9:40 AM
 */

package com.hendercine.android.bakinbuns.ui;

/**
 * BakinBuns created by hendercine on 9/22/17.
 */

//public class StepsDetailActivity extends AppCompatActivity {
//
//    @State(StepBundler.class)
//    Step mStep;
//    @State(StepListBundler.class)
//    ArrayList<Step> mStepsList;
//    @State
//    int step_index;
//
////    @Nullable
////    @BindView(R.id.next_step_btn)
////    Button nextStepButton;
////    @Nullable
////    @BindView(R.id.prev_step_btn)
////    Button prevStepButton;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Icepick.restoreInstanceState(this, savedInstanceState);
//
//        if (getResources().getConfiguration().orientation
//                == Configuration.ORIENTATION_LANDSCAPE) {
//            finish();
//        } else {
//
//            setContentView(R.layout.activity_step_detail);
////            ButterKnife.bind(this);
//
//            // Show the Up button in the action bar.
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setDisplayHomeAsUpEnabled(true);
//            }
//
////            mStep = Parcels.unwrap(getIntent().getParcelableExtra("current_step"));
////            mStepsList = Parcels.unwrap(getIntent().getParcelableExtra("steps_list"));
////            step_index = getIntent().getIntExtra("step_index", -1);
////
////            Bundle args = new Bundle();
////            args.putParcelable("current_step", Parcels.wrap(mStep));
////            args.putParcelable("steps_list", Parcels.wrap(mStepsList));
////            args.putInt("step_index", step_index);
//
//            StepsDetailFragment stepsDetailFragment = new StepsDetailFragment();
//            stepsDetailFragment
//                    .setArguments(getIntent().getExtras());
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.step_detail_fragment, stepsDetailFragment)
//                    .commit();
//        }
//    }
//}
