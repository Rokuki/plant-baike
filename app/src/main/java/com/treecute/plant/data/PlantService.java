package com.treecute.plant.data;

import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.model.RecognitionResult;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public interface PlantService {

    @GET Observable<PlantResponse> fetchRandPlant(@Url String url, @Query("count")int count);

    @GET Observable<PlantResponse> fetchPlantByCategory(@Url String url,@Query("category")String category);

    @GET Observable<List<PlantCategory>> fetchPlantCategory(@Url String url);

    @Multipart
    @POST Observable<RecognitionResponse> recognition(@Url String url, @Part MultipartBody.Part file);

}
