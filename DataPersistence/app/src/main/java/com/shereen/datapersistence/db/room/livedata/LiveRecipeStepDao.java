package com.shereen.datapersistence.db.room.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shereen.datapersistence.models.RecipeStep;

import java.util.List;

/**
 * Created by shereen on 12/30/18
 */
@Dao
public interface LiveRecipeStepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createRecipeSteps(List<RecipeStep> steps);

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    LiveData<List<RecipeStep>> getAllRecipeStepsByRecipeId(long recipeId);

    @Query("SELECT * FROM recipe_steps")
    LiveData<List<RecipeStep>> getAllRecipeSteps();

    @Query("DELETE FROM recipe_steps")
    void deleteAllRecipeSteps();

}
