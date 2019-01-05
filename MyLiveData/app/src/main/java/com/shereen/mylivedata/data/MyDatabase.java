package com.shereen.mylivedata.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shereen.mylivedata.data.entity.MyNumber;

/**
 * Created by shereen on 1/2/19
 */
@Database(entities = {MyNumber.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase INSTANCE = null;

    public static MyDatabase getInstance(Context context){

        if (INSTANCE == null) {
            synchronized (MyDatabase.class){
                INSTANCE = Room.databaseBuilder( context.getApplicationContext(),
                        MyDatabase.class,
                        Constants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }

    public abstract MyDao mydao();
}
