package com.shereen.mylivedata.domain;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.shereen.mylivedata.data.MyDao;
import com.shereen.mylivedata.data.MyDatabase;
import com.shereen.mylivedata.data.entity.MyNumber;

import java.util.List;

/**
 * Created by shereen on 1/2/19
 */

public class MyDataRepository {

    private MyDao mydao;

    public MyDataRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        mydao = database.mydao();
    }

    public void insertNum(MyNumber number){
        new createNumberAsyncTask(mydao).execute(number);
    }

    public LiveData<List<MyNumber>> getNumbers(){
        return mydao.getOneNumberList();
    }

    private static class createNumberAsyncTask extends AsyncTask<MyNumber, Void, Long> {

        private MyDao dao;

        createNumberAsyncTask(MyDao cdao){
            dao = cdao;
        }

        @Override
        protected Long doInBackground(MyNumber... numbers) {
            long id = dao.insertOneNumber(numbers[0]);
            return id;
        }
    }
}
