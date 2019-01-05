package com.shereen.testingapp.ui;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shereen.testingapp.data.model.Calculation;
import com.shereen.testingapp.data.room.CalcDatabase;

import java.util.List;

/**
 * Created by shereen on 12/31/18
 */

public class ViewModel extends AndroidViewModel {

    private final LiveData<List<Calculation>> calcList;
    private CalcDatabase database;

    public ViewModel(@NonNull Application application) {
        super(application);
        database = CalcDatabase.getInstance(this.getApplication());
        calcList = database.calcDao().getAllCalc();

    }



    public LiveData<List<Calculation>> getCalcList(){
        return calcList;
    }

    public void createCalc(Calculation calculation){
        new createCalcAsyncTask(database).execute(calculation);
    }

    public void deleteAllCalc(){
        new deleteAllCalcAsyncTask(database).execute();
    }

    private static class createCalcAsyncTask extends AsyncTask<Calculation, Void, Void> {

        private CalcDatabase db;

        createCalcAsyncTask(CalcDatabase database){
            db = database;
        }

        @Override
        protected Void doInBackground(Calculation... calculations) {
            db.calcDao().insertOneCalc(calculations[0]);
            return null;
        }
    }

    private static class deleteAllCalcAsyncTask extends AsyncTask<Void, Void, Void> {

        private CalcDatabase db;

        deleteAllCalcAsyncTask(CalcDatabase database){
            db = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.calcDao().deleteAllCalc();
            return null;
        }
    }


}
