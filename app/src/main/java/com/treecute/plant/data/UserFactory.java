package com.treecute.plant.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mkind on 2017/11/24 0024.
 */

public class UserFactory {
    public final static String BASE_URL = "http://120.25.1.26:97/";
    public final static String SIGN_UP_URL = "http://120.25.1.26:97/user/sign";
    public final static String LOGIN_URL = "http://120.25.1.26:97/user/login";

    public static UserService create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(UserService.class);
    }
}
