/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 6/26/17 8:59 AM
 */

package com.hendercine.android.bakinbuns.data.db;

import com.hendercine.android.bakinbuns.data.db.model.DaoMaster;
import com.hendercine.android.bakinbuns.data.db.model.DaoSession;
import com.hendercine.android.bakinbuns.data.db.model.Ingredient;
import com.hendercine.android.bakinbuns.data.db.model.Recipe;
import com.hendercine.android.bakinbuns.data.db.model.Step;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * BakinBuns created by hendercine on 6/26/17.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Recipe>> getAllRecipes() {
        return Observable.fromCallable(new Callable<List<Recipe>>() {
            @Override
            public List<Recipe> call() throws Exception {
                return mDaoSession.getRecipeDao().loadAll();
            }
        });
    }

    @Override
    public Observable<List<Step>> getAllRecipeSteps() {
        return Observable.fromCallable(new Callable<List<Step>>() {
            @Override
            public List<Step> call() throws Exception {
                return mDaoSession.getRecipeStepDao().loadAll();
            }
        });
    }

    @Override
    public Observable<List<Ingredient>> getAllIngredients() {
        return Observable.fromCallable(new Callable<List<Ingredient>>() {
            @Override
            public List<Ingredient> call() throws Exception {
                return mDaoSession.getIngredientsDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isRecipeEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getRecipeDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> isRecipeStepEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getRecipeStepDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> isIngredientEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getIngredientsDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveRecipe(final Recipe recipe) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRecipeDao().insert(recipe);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveRecipeStep(final Step step) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRecipeStepDao().insert(step);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveIngredient(final Ingredient ingredient) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getIngredientsDao().insert(ingredient);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveRecipeList(final List<Recipe> recipeList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRecipeDao().insertInTx(recipeList);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveRecipeStepList(final List<Step> stepList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRecipeStepDao().insertInTx(stepList);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveIngredientList(final List<Ingredient> ingredientList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getIngredientsDao().insertInTx(ingredientList);
                return true;
            }
        });
    }
}
