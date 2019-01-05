package com.shereen.mylivedata.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.shereen.mylivedata.domain.MyDataRepository;
import com.shereen.mylivedata.data.entity.MyNumber;

import java.util.List;

/**
 * Created by shereen on 1/2/19
 */

public class MyViewModel extends AndroidViewModel {

    MyDataRepository mrepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        mrepository = new MyDataRepository(application);
    }

    public LiveData<List<MyNumber>> getAllNumbers(){
        return mrepository.getNumbers();
    }

    public void insertNumber(MyNumber number){
        mrepository.insertNum(number);
    }
}
