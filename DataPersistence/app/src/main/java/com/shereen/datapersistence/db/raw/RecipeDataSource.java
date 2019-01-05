package com.shereen.datapersistence.db.raw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shereen on 12/29/18
 */

public class RecipeDataSource {

    private static final String TAG = RecipeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public RecipeDataSource(Context context){
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    public void open(){
        this.database = dbHelper.getWritableDatabase();
        Log.d(TAG, "-------------------------------------------------\nDatabase is opened");
    }

    public void close(){
        dbHelper.close();
        Log.d(TAG, "Database is closed");

    }

    public void createRecipe(Recipe recipe){
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        long rowId = database.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);
        Log.d(TAG, "Recipe with id: " + rowId);

        List<RecipeStep> steps = recipe.getSteps();
        if (steps != null && steps.size() > 0){
            for (RecipeStep step : steps) {
                createRecipeStep(step, rowId);
            }
        }
    }

    public void createRecipeStep(RecipeStep recipeStep, long recipeId){
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeStepEntry.COLUMN_RECIPE_ID, recipeStep.getRecipeId());
        values.put(RecipeContract.RecipeStepEntry.COLUMN_INSTRUCTION, recipeStep.getInstruction());
        values.put(RecipeContract.RecipeStepEntry.COLUMN_STEP_NUMBER, recipeStep.getStepNumber());

        long result = database.insert(RecipeContract.RecipeStepEntry.TABLE_NAME, null, values);
        Log.d(TAG, "Recipe step with id: " + result);
    }

    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM " + RecipeContract.RecipeEntry.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);
        try{
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String col1 = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME));
                String col2 = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION));
                int col3 = cursor.getInt(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID));

                Recipe recipe = new Recipe(id, col1, col2, col3);
                recipes.add(recipe);
            }

        }
        finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return recipes;
    }

    public void truncateTables(){

        database.execSQL("DELETE FROM " +RecipeContract.RecipeStepEntry.TABLE_NAME);
        database.execSQL("DELETE FROM " +RecipeContract.RecipeEntry.TABLE_NAME);
    }

    public void updateRecipe(Recipe recipe){

        Log.d(TAG, "Updated recipe: " + recipe.toString());

        ContentValues values = new ContentValues();
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        String selection = RecipeContract.RecipeEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(recipe.getId()) };

        int count = database.update(RecipeContract.RecipeEntry.TABLE_NAME, values, selection, selectionArgs);
        Log.d(TAG, "here: " + values);
        Log.d(TAG, "here: " + selection);
        Log.d(TAG, "here: " + selectionArgs[0]);
        Log.d(TAG, "Number of records updated: " + count);
    }

    public void deleteRecipe(Recipe recipe){

        String selection = RecipeContract.RecipeEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(recipe.getId()) };

        int countSteps = database.delete(RecipeContract.RecipeStepEntry.TABLE_NAME, selection, selectionArgs);

        int count = database.delete(RecipeContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);

        Log.d(TAG, "Number of records deleted: " + count + " steps: " +countSteps);
    }




}
