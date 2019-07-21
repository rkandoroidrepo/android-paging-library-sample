package com.paging.com.mysample.homescreen;

import com.paging.com.mysample.pojo.IMResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageServices {

    @GET("api/")
    Call<IMResponse> getAllImages(@Query("key") String apiKey, @Query("page") int page, @Query("per_page") String perPage);

    @GET("api/")
    Call<IMResponse> searchImages(@Query("key") String apiKey, @Query("q") String searchQuery);

}
