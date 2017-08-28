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

import com.hendercine.android.bakinbuns.data.models.Recipe;

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
    private static final String COLUMN_INGREDIENT_NAME = "ingredient";
    private static final String COLUMN_INGREDIENT_QUANTITY = "quantity";
    private static final String COLUMN_INGREDIENT_MEASURE = "measure";
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
        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_NAME
                + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RECIPE_ID + " TEXT NOT NULL, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_SERVINGS + " TEXT NOT NULL, " +
                COLUMN_INGREDIENT_NAME + " TEXT NOT NULL, " +
                COLUMN_INGREDIENT_QUANTITY + " TEXT NOT NULL, " +
                COLUMN_INGREDIENT_MEASURE + " TEXT NOT NULL, " +
                COLUMN_STEP_ID + " TEXT NOT NULL, " +
                COLUMN_SHORT_DESCRIPTION + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_VIDEO_URL + " TEXT" +
                COLUMN_THUMBNAIL_URL + " TEXT" +
                " );";
        db.execSQL(SQL_CREATE_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_ID, recipe.getRecipeId());
        values.put(COLUMN_RECIPE_NAME, recipe.getRecipeName());
        values.put(COLUMN_INGREDIENT_NAME, recipe.getIngredientName());
        values.put(COLUMN_INGREDIENT_QUANTITY, recipe.getIngredientQuantity());
        values.put(COLUMN_INGREDIENT_MEASURE, recipe.getIngredientMeasure());
        values.put(COLUMN_SERVINGS, recipe.getServings());
        values.put(COLUMN_STEP_ID, recipe.getStepId());
        values.put(COLUMN_SHORT_DESCRIPTION, recipe.getShortDescription());
        values.put(COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(COLUMN_VIDEO_URL, recipe.getVideoURL());
        values.put(COLUMN_THUMBNAIL_URL, recipe.getThumbnailURL());
        db.insert(TABLE_NAME, null, values);
        values.clear();
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
        recipe.setIngredientName(cursor
                .getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)));
        recipe.setIngredientQuantity(Integer.parseInt(cursor
                .getString(cursor.getColumnIndex(COLUMN_INGREDIENT_QUANTITY))));
        recipe.setIngredientMeasure(cursor
                .getString(cursor.getColumnIndex(COLUMN_INGREDIENT_MEASURE)));
        recipe.setServings(Integer.parseInt(cursor
                .getString(cursor.getColumnIndex(COLUMN_SERVINGS))));
        recipe.setStepId(cursor
                .getString(cursor.getColumnIndex(COLUMN_STEP_ID)));
        recipe.setShortDescription(cursor
                .getString(cursor.getColumnIndex(COLUMN_SHORT_DESCRIPTION)));
        recipe.setDescription(cursor
                .getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        recipe.setVideoURL(cursor
                .getString(cursor.getColumnIndex(COLUMN_VIDEO_URL)));
        recipe.setThumbnailURL(cursor
                .getString(cursor.getColumnIndex(COLUMN_THUMBNAIL_URL)));
        cursor.close();
        db.close();
        return recipe;
    }
}
