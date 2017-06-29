/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/23/17 5:09 PM
 */

package com.hendercine.android.bakinbuns.data;

import com.hendercine.android.bakinbuns.data.db.DbHelper;

import io.reactivex.Observable;

/**
 * BakinBuns created by hendercine on 6/23/17.
 */

public interface DataManager extends DbHelper {

    Observable<Boolean> addDatabaseRecipes();

    Observable<Boolean> addDatabaseRecipeSteps();

    Observable<Boolean> addDatabaseIngredients();

}
