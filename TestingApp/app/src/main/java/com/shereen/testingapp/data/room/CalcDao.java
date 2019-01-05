package com.shereen.testingapp.data.room;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shereen.testingapp.data.Constants;
import com.shereen.testingapp.data.model.Calculation;

import java.util.List;

/**
 * Created by shereen on 12/31/18
 */
@Dao
public interface CalcDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOneCalc(Calculation calculation);

    @Query("SELECT * FROM " + Constants.CALC_TABLE_NAME)
    LiveData<List<Calculation>> getAllCalc();

    @Query("SELECT * FROM " + Constants.CALC_TABLE_NAME)
    DataSource.Factory<Integer, Calculation> getAllPagedCalc();

    @Query("DELETE FROM " + Constants.CALC_TABLE_NAME)
    void deleteAllCalc();

//    @Query("SELECT * FROM " + Constants.CALC_TABLE_NAME +" ORDER BY calculationId DESC LIMIT :page ")
//    LivePagedListBuilder<Long, Calculation> getPagedCalc(int page);
//    //LiveData<List<Calculation>> getPagedCalc(int page);
}
