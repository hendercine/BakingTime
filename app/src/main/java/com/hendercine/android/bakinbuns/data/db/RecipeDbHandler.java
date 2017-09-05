/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 8/27/17 3:18 PM
 */

package com.hendercine.android.bakinbuns.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hendercine.android.bakinbuns.data.models.Ingredient;
import com.hendercine.android.bakinbuns.data.models.Recipe;
import com.hendercine.android.bakinbuns.data.models.Step;

import java.util.ArrayList;

/**
 * BakinBuns created by hendercine on 8/27/17.
 */

public class RecipeDbHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "recipes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_RECIPE_NAME = "name";
    private static final String COLUMN_SERVINGS = "servings";
    private static final String COLUMN_INGREDIENT_KEY = "ingredient_key";
    private static final String COLUMN_STEP_KEY = "step_key";

    private static final String TABLE_NAME_INGREDIENTS = "ingredients";
    private static final String COLUMN_INGREDIENT_NAME = "ingredient";
    private static final String COLUMN_INGREDIENT_QUANTITY = "quantity";
    private static final String COLUMN_INGREDIENT_MEASURE = "measure";

    private static final String TABLE_NAME_STEPS = "steps";
    private static final String COLUMN_STEP_ID = "step_id";
    private static final String COLUMN_SHORT_DESCRIPTION = "short_description";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_VIDEO_URL = "video_url";
    private static final String COLUMN_THUMBNAIL_URL = "thumbnail_url";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recipes.db";

    public RecipeDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_RECIPES_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RECIPE_ID + " TEXT NOT NULL, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_SERVINGS + " TEXT NOT NULL, " +
                COLUMN_INGREDIENT_KEY + " TEXT NOT NULL, " +
                COLUMN_STEP_KEY + " TEXT" +
                " FOREIGN KEY (" + COLUMN_INGREDIENT_KEY + ") REFERENCES " +
                TABLE_NAME_INGREDIENTS + " (" + COLUMN_INGREDIENT_KEY + "), "
                + " FOREIGN KEY (" + COLUMN_STEP_KEY + ") REFERENCES " +
                TABLE_NAME_STEPS + " (" + COLUMN_STEP_KEY + ");";

        final String SQL_CREATE_INGREDIENTS_TABLE = "CREATE TABLE " +
                TABLE_NAME_INGREDIENTS + " (" +
                COLUMN_INGREDIENT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RECIPE_ID + " TEXT NOT NULL, " +
                COLUMN_INGREDIENT_QUANTITY + " TEXT NOT NULL, " +
                COLUMN_INGREDIENT_MEASURE + " TEXT NOT NULL, " +
                " );";

        final String SQL_CREATE_STEPS_TABLE = "CREATE TABLE " +
                TABLE_NAME_STEPS + " (" +
                COLUMN_STEP_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RECIPE_ID + " TEXT NOT NULL, " +
                COLUMN_STEP_ID + " TEXT NOT NULL, " +
                COLUMN_SHORT_DESCRIPTION + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_VIDEO_URL + " TEXT" +
                COLUMN_THUMBNAIL_URL + " TEXT" +
                " );";

        db.execSQL(SQL_CREATE_INGREDIENTS_TABLE);
        db.execSQL(SQL_CREATE_STEPS_TABLE);
        db.execSQL(SQL_CREATE_RECIPES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STEPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_ID, recipe.getRecipeId());
        values.put(COLUMN_RECIPE_NAME, recipe.getRecipeName());
        values.put(COLUMN_SERVINGS, recipe.getServings());
        db.insert(TABLE_NAME, null, values);
        values.clear();
        db.close();
    }

    public void addIngredients(Recipe recipe, Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ingredientValues = new ContentValues();

        ingredientValues.put(COLUMN_RECIPE_ID, recipe.getRecipeId());
        ingredientValues.put(COLUMN_INGREDIENT_NAME, ingredient.getIngredientName());
        ingredientValues.put(COLUMN_INGREDIENT_QUANTITY, ingredient.getIngredientQuantity());
        ingredientValues.put(COLUMN_INGREDIENT_MEASURE, ingredient.getIngredientMeasure());
        db.insert(TABLE_NAME_INGREDIENTS, null, ingredientValues);
        ingredientValues.clear();
        db.close();
    }

    public void addSteps(Recipe recipe, Step step) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues stepValues = new ContentValues();

        stepValues.put(COLUMN_RECIPE_ID, recipe.getRecipeId());
        stepValues.put(COLUMN_STEP_ID, step.getStepId());
        stepValues.put(COLUMN_SHORT_DESCRIPTION, step.getShortDescription());
        stepValues.put(COLUMN_DESCRIPTION, step.getDescription());
        stepValues.put(COLUMN_VIDEO_URL, step.getVideoURL());
        stepValues.put(COLUMN_THUMBNAIL_URL, step.getThumbnailURL());
        db.insert(TABLE_NAME_STEPS, null, stepValues);
        stepValues.clear();
        db.close();
    }

    public String fetchVideoPath(String recipeId) {
        String listQuery = "SELECT " + COLUMN_STEP_ID +
                ", " + COLUMN_VIDEO_URL +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_RECIPE_ID +
                " = '" + recipeId + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(listQuery, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(COLUMN_STEP_ID));
        cursor.close();
        db.close();
        return path;
    }

    public ArrayList<String> fetchRecipeIds() {
        String listQuery = "SELECT " + COLUMN_RECIPE_ID +
                " FROM " + TABLE_NAME +
                " ORDER BY " + COLUMN_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(listQuery, null);
        ArrayList<String> ids = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_ID));
                ids.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ids;
    }

    public Recipe fetchRecipeDetails(String id) {
        String listQuery = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_RECIPE_ID +
                " = '" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(listQuery, null);
        Recipe recipe = new Recipe();

        cursor.moveToFirst();
        recipe.setRecipeId(Integer.parseInt(cursor
                .getString(cursor.getColumnIndex(COLUMN_RECIPE_ID))));
        recipe.setRecipeName(cursor
                .getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME)));
        recipe.setServings(Integer.parseInt(cursor
                .getString(cursor.getColumnIndex(COLUMN_SERVINGS))));
        cursor.close();
        db.close();
        return recipe;
    }

    private Step parseSteps(String id) {
        String listQuery = "SELECT * FROM " + TABLE_NAME_STEPS + " WHERE "
                + COLUMN_RECIPE_ID + " = '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(listQuery, null);
        Step steps = new Step();

        cursor.moveToFirst();
        steps.setStepId(cursor
                .getString(cursor.getColumnIndex(COLUMN_STEP_ID)));
        steps.setShortDescription(cursor
                .getString(cursor.getColumnIndex(COLUMN_SHORT_DESCRIPTION)));
        steps.setDescription(cursor
                .getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        steps.setVideoURL(cursor
                .getString(cursor.getColumnIndex(COLUMN_VIDEO_URL)));
        steps.setThumbnailURL(cursor
                .getString(cursor.getColumnIndex(COLUMN_THUMBNAIL_URL)));
        cursor.close();
        db.close();
        return steps;
    }

    private Ingredient parseIngredients(String id) {
        String listQuery = "SELECT * FROM " + TABLE_NAME_INGREDIENTS + " WHERE "
        + COLUMN_RECIPE_ID + " = '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(listQuery, null);
        Ingredient ingredients = new Ingredient();

        cursor.moveToFirst();
        ingredients.setIngredientName(cursor
                .getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)));
        ingredients.setIngredientQuantity(Integer.parseInt(cursor
                .getString(cursor.getColumnIndex(COLUMN_INGREDIENT_QUANTITY))));
        ingredients.setIngredientMeasure(cursor
                .getString(cursor.getColumnIndex(COLUMN_INGREDIENT_MEASURE)));
        cursor.close();
        db.close();
        return ingredients;
    }
}
