package com.shereen.datapersistence.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

import java.util.List;

/**
 * Created by shereen on 12/29/18
 */

@Dao
public interface RecipeStepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createRecipeSteps(List<RecipeStep> steps);

    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    List<RecipeStep> getAllRecipeStepsByRecipeId(long recipeId);

    @Query("DELETE FROM recipe_steps")
    void deleteAllRecipeSteps();
}
