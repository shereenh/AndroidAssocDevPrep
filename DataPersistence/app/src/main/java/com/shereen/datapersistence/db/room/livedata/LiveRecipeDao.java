package com.shereen.datapersistence.db.room.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shereen.datapersistence.models.Recipe;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by shereen on 12/30/18
 */

@Dao
public interface LiveRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("DELETE FROM recipe")
    void deleteAllRecipes();

    @Delete
    void deleteRecipe(Recipe recipe);

}
