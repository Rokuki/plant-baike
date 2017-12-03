package com.treecute.plant.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public class GoodsFactory {
    public final static String BASE_URL = "http://120.25.1.26:97/";
    public final static String GET_GOODS = "http://120.25.1.26:97/goods/get";

    public static GoodsService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GoodsService.class);
    }

}
