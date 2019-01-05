package com.shereen.mylivedata.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shereen.mylivedata.data.entity.MyNumber;

import java.util.List;

/**
 * Created by shereen on 1/2/19
 */
@Dao
public interface  MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOneNumber(MyNumber number);

    @Query("SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY id DESC LIMIT 1")
    LiveData<List<MyNumber>> getOneNumberList();
}
