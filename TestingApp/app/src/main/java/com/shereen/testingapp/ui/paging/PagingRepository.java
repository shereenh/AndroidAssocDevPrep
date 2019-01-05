package com.shereen.testingapp.ui.paging;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.AsyncTask;

import com.shereen.testingapp.data.Constants;
import com.shereen.testingapp.data.model.Calculation;
import com.shereen.testingapp.data.room.CalcDao;
import com.shereen.testingapp.data.room.CalcDatabase;

/**
 * Created by shereen on 1/1/19
 */

public class PagingRepository {

    private CalcDao calcDao;

    //private final LiveData<PagedList<Calculation>> pagedCalcList;

    private final static PagedList.Config config
            = new PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.PAGE_INITIAL_LOAD_SIZE_HINT)
            .setPrefetchDistance(Constants.PAGE_PREFETCH_DISTANCE)
            .setEnablePlaceholders(true)
            .build();

    public PagingRepository(Application application) {
        CalcDatabase database = CalcDatabase.getInstance(application);
        calcDao = database.calcDao();
        //pagedCalcList = new LivePagedListBuilder<>( database.calcDao().getAllPagedCalc(), 10).build();
    }

    public void insertCalculation(Calculation calculation){
        new createCalcAsyncTask(calcDao).execute(calculation);
    }

    public LiveData<PagedList<Calculation>> getAllCalulations(){
        DataSource.Factory<Integer, Calculation> factory = calcDao.getAllPagedCalc();
        return new LivePagedListBuilder<>(factory, config).build();
    }

    public void deleteAllCalculations(){
        new deleteAllCalcAsyncTask(calcDao).execute();
    }


    private static class createCalcAsyncTask extends AsyncTask<Calculation, Void, Long> {

        private CalcDao dao;

        createCalcAsyncTask(CalcDao cdao){
            dao = cdao;
        }

        @Override
        protected Long doInBackground(Calculation... calculations) {
            long id = dao.insertOneCalc(calculations[0]);
            return id;
        }
    }

    private static class deleteAllCalcAsyncTask extends AsyncTask<Void, Void, Void> {

        private CalcDao dao;

        deleteAllCalcAsyncTask(CalcDao cdao){
            dao = cdao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllCalc();
            return null;
        }
    }



}
