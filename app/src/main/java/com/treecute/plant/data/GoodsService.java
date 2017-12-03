package com.treecute.plant.data;

import android.databinding.ObservableInt;

import io.reactivex.Observable;
import retrofit2.http.GET;
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

}
