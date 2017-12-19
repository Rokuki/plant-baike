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
    public final static String ADD = "http://120.25.1.26:97/goods/add";
    public final static String DELETE = "http://120.25.1.26:97/goods/delete";
    public final static String UPDATE = "http://120.25.1.26:97/goods/update";
    public final static String ADD_TO_COLLECTION = "http://120.25.1.26:97/goodsCollection/addToCollection";
    public final static String REMOVE_COLLECTION = "http://120.25.1.26:97/goodsCollection/removeCollection";
    public final static String GET_IF_COLLECTED = "http://120.25.1.26:97/goodsCollection/getIfCollected";
    public final static String ADD_TO_CART = "http://120.25.1.26:97/cart/addToCart";
    public final static String REMOVE_FROM_CART = "http://120.25.1.26:97/cart/removeFromCart";
    public final static String QUERY_CART = "http://120.25.1.26:97/cart/query";

    public static GoodsService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GoodsService.class);
    }

}
