package com.shereen.paginglibrary.middle;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.shereen.paginglibrary.data.AnsItemDataSource;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;
import com.shereen.paginglibrary.data.entity.ques.QuesItem;

/**
 * Created by shereen on 1/3/19
 */

public class AnsItemDataSourceFactory extends DataSource.Factory{

    private MutableLiveData<PageKeyedDataSource<Integer, AnsItem>> ansItemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        AnsItemDataSource ansItemDataSource = new AnsItemDataSource();
        ansItemLiveDataSource.postValue(ansItemDataSource);
        return ansItemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, AnsItem>> getItemLiveDataSource() {
        return ansItemLiveDataSource;
    }
}
