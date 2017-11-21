package com.treecute.plant.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class PlantFactory {
    private final static String BASE_URL = "http://120.25.1.26:97/";
    private final static String RAND_PLANT_URL = "http://120.25.1.26:97/plant/getRand?count=20";

    public static PlantService create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(PlantService.class);
    }

}
