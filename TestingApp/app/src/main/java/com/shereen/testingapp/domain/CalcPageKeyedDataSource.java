package com.shereen.testingapp.domain;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.shereen.testingapp.data.model.Calculation;

/**
 * Created by shereen on 1/1/19
 */

public class CalcPageKeyedDataSource extends PageKeyedDataSource<Long, Calculation> {


    private MutableLiveData networkState;

    private MutableLiveData initialLoading;


    public CalcPageKeyedDataSource(){
        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Calculation> callback) {


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Calculation> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Calculation> callback) {

    }
}
