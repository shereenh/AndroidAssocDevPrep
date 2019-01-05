package com.shereen.paginglibrary.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.shereen.paginglibrary.data.entity.ques.QuesItem;
import com.shereen.paginglibrary.data.entity.ques.QuesStackResponse;
import com.shereen.paginglibrary.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shereen.paginglibrary.data.Constants.FIRST_PAGE;
import static com.shereen.paginglibrary.data.Constants.PAGE_SIZE;
import static com.shereen.paginglibrary.data.Constants.SITE_NAME;

/**
 * Created by shereen on 1/5/19
 */

public class QuesItemDataSource extends PageKeyedDataSource<Integer, QuesItem> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, QuesItem> callback) {
        RetrofitClient.getInsance()
                .getApi()
                .getQuestions(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<QuesStackResponse>(){

                    @Override
                    public void onResponse(Call<QuesStackResponse> call, Response<QuesStackResponse> response) {
                        if(response.body() != null){
                            callback.onResult(response.body().quesItems, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuesStackResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, QuesItem> callback) {
        RetrofitClient.getInsance()
                .getApi()
                .getQuestions(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<QuesStackResponse>() {
                    @Override
                    public void onResponse(Call<QuesStackResponse> call, Response<QuesStackResponse> response) {
                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1: null;
                            callback.onResult(response.body().quesItems, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuesStackResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, QuesItem> callback) {
        RetrofitClient.getInsance()
                .getApi()
                .getQuestions(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<QuesStackResponse>() {
                    @Override
                    public void onResponse(Call<QuesStackResponse> call, Response<QuesStackResponse> response) {

                        if (response.body() != null) {
                            Integer key = response.body().has_more ? params.key +1 : null;
                            callback.onResult(response.body().quesItems, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<QuesStackResponse> call, Throwable t) {

                    }
                });
    }
}
