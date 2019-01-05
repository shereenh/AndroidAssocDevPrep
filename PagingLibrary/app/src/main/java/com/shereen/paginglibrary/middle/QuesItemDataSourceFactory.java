package com.shereen.paginglibrary.middle;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.shereen.paginglibrary.data.AnsItemDataSource;
import com.shereen.paginglibrary.data.QuesItemDataSource;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;
import com.shereen.paginglibrary.data.entity.ques.QuesItem;

/**
 * Created by shereen on 1/5/19
 */

public class QuesItemDataSourceFactory extends DataSource.Factory{

    private MutableLiveData<PageKeyedDataSource<Integer, QuesItem>> quesItemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        QuesItemDataSource quesItemDataSource = new QuesItemDataSource();
        quesItemLiveDataSource.postValue(quesItemDataSource);
        return quesItemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, QuesItem>> getItemLiveDataSource() {
        return quesItemLiveDataSource;
    }
}