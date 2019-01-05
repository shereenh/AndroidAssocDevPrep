package com.shereen.testingapp.ui.paging;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.shereen.testingapp.data.model.Calculation;
import com.shereen.testingapp.ui.paging.PagingRepository;

/**
 * Created by shereen on 1/1/19
 */

public class PagingViewModel extends AndroidViewModel {

    private PagingRepository mrepository;
    private LiveData<PagedList<Calculation>> pagedCalcList;


    public PagingViewModel(@NonNull Application application) {
        super(application);

        mrepository = new PagingRepository(application);
    }

    public LiveData<PagedList<Calculation>> getPagedCalculations(){
        if(pagedCalcList == null){
            pagedCalcList = mrepository.getAllCalulations();
        }
        return pagedCalcList;
    }

    public void insertCalculation(Calculation calculation){
        mrepository.insertCalculation(calculation);
    }

    public void deleteAllCalculations(){
        mrepository.deleteAllCalculations();
    }
}
