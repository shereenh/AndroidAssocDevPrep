package com.shereen.datapersistence.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shereen.datapersistence.models.Recipe;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by shereen on 12/29/18
 */

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe")
    Flowable<List<Recipe>> getAllRecipes();

    @Query("DELETE FROM recipe")
    void deleteAllRecipes();


}
