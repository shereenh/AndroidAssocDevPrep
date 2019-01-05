package com.shereen.datapersistence.db.room.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;

import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by shereen on 12/31/18
 */

public class LiveDatabaseHelper {

    private LiveRecipeDao recipeDao;
    private LiveRecipeStepDao recipeStepDao;

    @Inject
    public LiveDatabaseHelper(LiveRecipeDatabase database){
        recipeDao = database.recipeDao();
        recipeStepDao = database.recipeStepDao();
    }

    public LiveData<List<Recipe>> getRecipes(){
        LiveData<List<Recipe>> recipesLiveData = recipeDao.getAllRecipes();
        recipesLiveData = Transformations.switchMap(recipesLiveData, new Function<List<Recipe>, LiveData<List<Recipe>>>() {

            @Override
            public LiveData<List<Recipe>> apply(final List<Recipe> inputRecipes) {
                final MediatorLiveData<List<Recipe>> recipesMediatorLiveData = new MediatorLiveData<>();
                for (final Recipe recipe : inputRecipes) {

                    recipesMediatorLiveData.addSource(recipeStepDao.getAllRecipeStepsByRecipeId(recipe.getRecipeId()), new Observer<List<RecipeStep>>() {
                        @Override
                        public void onChanged(@Nullable List<RecipeStep> recipeSteps) {
                            recipe.setSteps(recipeSteps);
                            recipesMediatorLiveData.postValue(inputRecipes);
                        }
                    });
                }
                return recipesMediatorLiveData;
            }
        });
        return recipesLiveData;
    }




}
