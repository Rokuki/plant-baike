package com.treecute.plant.data;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.treecute.plant.model.Response;
import com.treecute.plant.model.ResponseResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public interface GoodsService {

    @GET
    Observable<GoodsResponse> getGoodsList(@Url String url,
                                           @Query("from") int from,
                                           @Query("offset") int offset);

    @POST
    Observable<ResponseResult<Response>> add(@Url String url,
                                             @Query("token") String token,
                                             @Query("username") String username,
                                             @Query("seller_id") int sellerId,
                                             @Query("price") double price,
                                             @Query("quantity") int quantity,
                                             @Query("title") String title,
                                             @Query("content") String content,
                                             @Query("plant_id") int plantId);

    @GET
    Observable<ResponseResult<Response>> getIfCollected(@Url String url,
                                                        @Query("token") String token,
                                                        @Query("username") String username,
                                                        @Query("userId") int userId,
                                                        @Query("goodsId") int goodsId);

    @POST
    Observable<ResponseResult<Response>> addToCollection(@Url String url,
                                                         @Query("token") String token,
                                                         @Query("username") String username,
                                                         @Query("userId") int userId,
                                                         @Query("goodsId") int goodsId);

    @POST
    Observable<ResponseResult<Response>> removeCollection(@Url String url,
                                                          @Query("token") String token,
                                                          @Query("username") String username,
                                                          @Query("userId") int userId,
                                                          @Query("goodsId") int goodsId);


}
