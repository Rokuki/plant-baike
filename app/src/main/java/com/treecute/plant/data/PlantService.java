package com.treecute.plant.data;


import android.databinding.ObservableInt;

import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.model.PlantCollections;
import com.treecute.plant.model.Response;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.SearchSuggestions;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
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
    @Multipart @POST Observable<RecognitionResponse> recognition(@Url String url, @Part MultipartBody.Part file);
    @GET Observable<PlantResponse> getPlantsByNameList(@Url String url,@Query("list")String names);
    @GET Observable<List<SearchSuggestions>> getSearchSuggestionsList(@Url String url, @Query("key")String key);
    @GET Observable<PlantResponse> getPlantBySearchPlace(@Url String url,@Query("key")String key,@Query("search_place")String searchPlace);

    @GET
    Observable<ResponseResult<Response>> getIfCollected(@Url String url,
                                                        @Query("token") String token,
                                                        @Query("username") String username,
                                                        @Query("userId") int userId,
                                                        @Query("plantId") int plantId);

    @POST
    Observable<ResponseResult<Response>> addToCollection(@Url String url,
                                                         @Query("token") String token,
                                                         @Query("username") String username,
                                                         @Query("userId") int userId,
                                                         @Query("plantId") int plantId);

    @POST
    Observable<ResponseResult<Response>> removeCollection(@Url String url,
                                                          @Query("token") String token,
                                                          @Query("username") String username,
                                                          @Query("userId") int userId,
                                                          @Query("plantId") int plantId);

    @GET
    Observable<PlantCollectionsResponse> queryCollections(@Url String url,
                                                          @Query("token") String token,
                                                          @Query("username") String username,
                                                          @Query("userId") int userId);
}
