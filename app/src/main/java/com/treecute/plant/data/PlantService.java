package com.treecute.plant.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public interface PlantService {

    @GET Observable<PlantResponse> fetchRandPlant(@Url String url);

    @GET Observable<UserResponse> fetchUser(@Url String url);

}
