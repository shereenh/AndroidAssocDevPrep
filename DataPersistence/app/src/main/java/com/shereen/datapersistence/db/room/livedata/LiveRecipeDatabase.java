package com.shereen.datapersistence.db.room.livedata;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.shereen.datapersistence.db.room.RecipeDao;
import com.shereen.datapersistence.db.room.RecipeDatabase;
import com.shereen.datapersistence.db.room.RecipeStepDao;
import com.shereen.datapersistence.models.Recipe;
import com.shereen.datapersistence.models.RecipeStep;

/**
 * Created by shereen on 12/30/18
 */

@Database(entities = {Recipe.class, RecipeStep.class}, version = 2, exportSchema = false)
public abstract class LiveRecipeDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "topsyLiver";
    private static LiveRecipeDatabase INSTANCE = null;

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE recipe ADD COLUMN number_of_stars INTEGER");
        }
    };

    public static LiveRecipeDatabase getInstance(Context context){

        if(INSTANCE == null){
            synchronized (LiveRecipeDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LiveRecipeDatabase.class,
                        DATABASE_NAME)
                        //.addMigrations(MIGRATION_1_2)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }

    public abstract LiveRecipeDao recipeDao();

    public abstract LiveRecipeStepDao recipeStepDao();

}
