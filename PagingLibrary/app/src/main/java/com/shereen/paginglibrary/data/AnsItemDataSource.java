package com.shereen.paginglibrary.data;

/**
 * Created by shereen on 1/3/19
 */

import android.arch.paging.PageKeyedDataSource;

import android.support.annotation.NonNull;

import com.shereen.paginglibrary.data.entity.ans.AnsStackResponse;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;
import com.shereen.paginglibrary.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shereen.paginglibrary.data.Constants.FIRST_PAGE;
import static com.shereen.paginglibrary.data.Constants.PAGE_SIZE;
import static com.shereen.paginglibrary.data.Constants.SITE_NAME;

public class AnsItemDataSource extends PageKeyedDataSource<Integer, AnsItem> {




    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, AnsItem> callback) {

        RetrofitClient.getInsance()
                .getApi()
                .getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<AnsStackResponse>() {
                    @Override
                    public void onResponse(Call<AnsStackResponse> call, Response<AnsStackResponse> response) {

                        if(response.body() != null){

                            callback.onResult(response.body().ansItems, null, FIRST_PAGE + 1);

                        }

                    }

                    @Override
                    public void onFailure(Call<AnsStackResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, AnsItem> callback) {

        RetrofitClient.getInsance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<AnsStackResponse>() {
                    @Override
                    public void onResponse(Call<AnsStackResponse> call, Response<AnsStackResponse> response) {

                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().ansItems, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<AnsStackResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, AnsItem> callback) {

        RetrofitClient.getInsance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<AnsStackResponse>() {
                    @Override
                    public void onResponse(Call<AnsStackResponse> call, Response<AnsStackResponse> response) {

                        if(response.body() != null){
                            Integer key = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().ansItems, key);
                        }

                    }

                    @Override
                    public void onFailure(Call<AnsStackResponse> call, Throwable t) {

                    }
                });
    }
}