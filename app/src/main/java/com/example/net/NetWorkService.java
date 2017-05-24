package com.example.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuyao on 2017/4/7 0007.
 */

public class NetWorkService {
    public static final String MOB_LIST_BASE = "http://m.toutiao.chinaso.com";
    static NetWorkServiceAPI instance;
    static public NetWorkServiceAPI getInstance(){
        if (instance != null)
            return instance;

        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(MOB_LIST_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        instance = restAdapter.create(NetWorkServiceAPI.class);

        return instance;
    }
}
