package com.shereen.paginglibrary.middle;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.content.ClipData;

import com.shereen.paginglibrary.data.ItemDataSource;
import com.shereen.paginglibrary.data.entity.Item;

/**
 * Created by shereen on 1/3/19
 */

public class ItemDataSourceFactory  extends DataSource.Factory{

    private MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
