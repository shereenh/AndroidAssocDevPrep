package com.shereen.roomtest;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by shereen on 1/1/19
 */
@android.arch.persistence.room.Dao
public interface PDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createPerson(Person person);

    @Query("SELECT * FROM Person")
    LiveData<List<Person>> getAllPeople();
}
