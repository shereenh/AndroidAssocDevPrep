package com.shereen.datapersistence.features.recipes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shereen.datapersistence.db.room.livedata.LiveDatabaseHelper;
import com.shereen.datapersistence.db.room.livedata.LiveRecipeDatabase;
import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

import java.util.List;

/**
 * Created by shereen on 12/30/18
 */

public class RecipeViewModel extends AndroidViewModel {


    private final LiveData<List<Recipe>> recipes;
    private LiveData<List<RecipeStep>> recipeSteps;

    private LiveRecipeDatabase recipeDatabase;
    private LiveDatabaseHelper liveDatabaseHelper;

    public RecipeViewModel(@NonNull Application application) {
        super(application);

        recipeDatabase = LiveRecipeDatabase.getInstance(this.getApplication());
        liveDatabaseHelper = new LiveDatabaseHelper(recipeDatabase);
        //recipes = recipeDatabase.recipeDao().getAllRecipes();
        recipes = liveDatabaseHelper.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipeList(){
        return recipes;
    }

    public void addItem(Recipe recipe){
        new addAsyncTask(recipeDatabase).execute(recipe);
    }

    public LiveData<List<RecipeStep>> getSteps(){
        return recipeDatabase.recipeStepDao().getAllRecipeSteps();
    }

//    public LiveData<List<RecipeStep>> getStepsById(long recipeId){
//        return recipeDatabase.recipeStepDao().getAllRecipeStepsByRecipeId(recipeId);
//    }

    public void deleteAllRecipes(){
        new deleteAllAsyncTask(recipeDatabase).execute();
    }
//
//    public void deleteItem(Recipe recipe){
//        new deleteAsyncTask(recipeDatabase).execute(recipe);
//    }


//    private static class deleteAsyncTask extends android.os.AsyncTask<Recipe, Void, Void> {
//
//        private LiveRecipeDatabase db;
//
//        deleteAsyncTask(LiveRecipeDatabase liveRecipeDatabase){
//            db = liveRecipeDatabase;
//        }
//
//        @Override
//        protected Void doInBackground(final Recipe... recipes) {
//            db.recipeDao().deleteRecipe(recipes[0]);
//            return null;
//        }
//    }

    private static class addAsyncTask extends android.os.AsyncTask<Recipe, Void, Void> {

        private LiveRecipeDatabase db;

        addAsyncTask(LiveRecipeDatabase liveRecipeDatabase){
            db = liveRecipeDatabase;
        }

        @Override
        protected Void doInBackground(final Recipe... recipes) {
            db.recipeDao().createRecipe(recipes[0]);
            List<RecipeStep> steps = recipes[0].getSteps();
            if (steps != null) {
                db.recipeStepDao().createRecipeSteps(steps);
            }
            return null;
        }
    }

    private static class deleteAllAsyncTask extends android.os.AsyncTask<Void, Void, Void> {

        private LiveRecipeDatabase db;

        deleteAllAsyncTask(LiveRecipeDatabase liveRecipeDatabase){
            db = liveRecipeDatabase;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            db.recipeStepDao().deleteAllRecipeSteps();
            db.recipeDao().deleteAllRecipes();
            return null;
        }
    }
}
