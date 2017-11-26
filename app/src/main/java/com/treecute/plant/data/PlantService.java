package com.treecute.plant.data;

import com.treecute.plant.model.PlantCategory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public interface PlantService {

    @GET Observable<PlantResponse> fetchRandPlant(@Url String url, @Query("count")int count);

    @GET Observable<PlantResponse> fetchPlantByCategory(@Url String url,@Query("category")String category);

    @GET Observable<List<PlantCategory>> fetchPlantCategory(@Url String url);

}
