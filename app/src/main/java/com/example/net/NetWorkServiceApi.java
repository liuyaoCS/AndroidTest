package com.example.net;

import com.example.entity.newsList.NewsAll;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by liuyao on 2017/4/7 0007.
 */

public interface NetworkServiceAPI {
    //list
    @GET("1/category/main")
    Call<NewsAll> fetchList(@Query("id") String id, @Query("page") int page);
}
