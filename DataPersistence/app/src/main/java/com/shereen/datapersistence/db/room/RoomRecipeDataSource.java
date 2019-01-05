package com.shereen.datapersistence.db.room;

import android.content.Context;
import android.util.Log;

import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by shereen on 12/29/18
 */

public class RoomRecipeDataSource {

    private static final String TAG = RoomRecipeDataSource.class.getSimpleName();

    private RecipeDao recipeDao;
    private RecipeStepDao recipeStepDao;

    public RoomRecipeDataSource(Context context){

        RecipeDatabase database = RecipeDatabase.getInstance(context);
        recipeDao = database.recipeDao();
        recipeStepDao = database.recipeStepDao();

    }

    public void createRecipe(Recipe recipe){
        long rowId = recipeDao.createRecipe(recipe);
        List<RecipeStep> steps = recipe.getSteps();
        if(steps != null){
            for (RecipeStep step : steps) {
                step.setRecipeId(rowId);
            }
            recipeStepDao.createRecipeSteps(steps);
        }
        Log.d(TAG, "Recipe with id: " + rowId);
    }

    public Flowable<List<Recipe>> getAllRecipes(){
//        List<Recipe> recipes = recipeDao.getAllRecipes();
//        for (Recipe recipe : recipes) {
//            List<RecipeStep> steps = recipeStepDao.getAllRecipeStepsByRecipeId(recipe.getId());
//            if (steps != null) {
//                recipe.setSteps(steps);
//            }
//        }
//        return recipes;

        return recipeDao.getAllRecipes();
    }

    public void truncateTables(){

        recipeStepDao.deleteAllRecipeSteps();
        recipeDao.deleteAllRecipes();
    }


    public void updateRecipe(Recipe recipe){

        long count = -1;

        Log.d(TAG, "Updated recipe: " + recipe.toString());

        Log.d(TAG, "Number of records updated: " + count);
    }

    public void deleteRecipe(Recipe recipe){

        int countSteps = -1;

        int count = -1;

        Log.d(TAG, "Number of records deleted: " + count + " steps: " +countSteps);
    }


}
