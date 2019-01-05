package com.shereen.testingapp.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shereen.testingapp.data.Constants;
import com.shereen.testingapp.data.model.Calculation;

/**
 * Created by shereen on 12/31/18
 */

@Database(entities = {Calculation.class}, version = 1, exportSchema = false)
public abstract class CalcDatabase extends RoomDatabase {

    private static CalcDatabase INSTANCE = null;

    public static CalcDatabase getInstance(Context context){

        if (INSTANCE == null) {
            synchronized (CalcDatabase.class){
                INSTANCE = Room.databaseBuilder( context.getApplicationContext(),
                        CalcDatabase.class,
                        Constants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }

    public abstract CalcDao calcDao();

}
