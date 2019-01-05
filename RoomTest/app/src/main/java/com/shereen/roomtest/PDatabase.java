package com.shereen.roomtest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by shereen on 1/1/19
 */

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class PDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "topsyLiver";
    private static PDatabase INSTANCE = null;


    private static PDatabase getInstance(Context context){
        if (INSTANCE == null) {
            synchronized (PDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PDatabase.class,
                        DATABASE_NAME)
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract PDao dao();
}
