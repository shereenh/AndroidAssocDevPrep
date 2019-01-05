package com.shereen.paginglibrary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.shereen.paginglibrary.data.Constants;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;
import com.shereen.paginglibrary.data.entity.ques.QuesItem;
import com.shereen.paginglibrary.middle.AnsItemDataSourceFactory;
import com.shereen.paginglibrary.middle.QuesItemDataSourceFactory;

/**
 * Created by shereen on 1/4/19
 */

public class ItemViewModel extends ViewModel {

    LiveData<PagedList<AnsItem>> ansItemPagedList;
    LiveData<PagedList<QuesItem>> quesItemPagedList;
    LiveData<PageKeyedDataSource<Integer, AnsItem>> ansLiveDataSource;
    LiveData<PageKeyedDataSource<Integer, QuesItem>> quesLiveDataSource;

    public ItemViewModel() {

        AnsItemDataSourceFactory ansItemDataSourceFactory = new AnsItemDataSourceFactory();
        QuesItemDataSourceFactory quesItemDataSourceFactory = new QuesItemDataSourceFactory();
        ansLiveDataSource = ansItemDataSourceFactory.getItemLiveDataSource();
        quesLiveDataSource = quesItemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(Constants.PAGE_SIZE)
                        .build();

        ansItemPagedList = (new LivePagedListBuilder(ansItemDataSourceFactory, config)).build();
        quesItemPagedList = (new LivePagedListBuilder(quesItemDataSourceFactory, config)).build();

    }
}
