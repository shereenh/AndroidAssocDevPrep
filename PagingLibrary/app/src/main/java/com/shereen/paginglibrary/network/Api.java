package com.shereen.paginglibrary.network;

import com.shereen.paginglibrary.data.entity.StackApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shereen on 1/3/19
 */

public interface Api {

    @GET("answers")
    Call<StackApiResponse> getAnswers(
            @Query("page") int page,
            @Query("pagesize") int size,
            @Query("site") String site
    );
}
