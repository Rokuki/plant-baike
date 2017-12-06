package com.treecute.plant.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class PlantFactory {
    public final static String BASE_URL = "http://120.25.1.26:97/";
    public final static String RAND_PLANT_URL = "http://120.25.1.26:97/plant/getRand";
    public final static String CATEGORY_LIST_URL = "http://120.25.1.26:97/plant/getCategoryList";
    public final static String GET_PLANT_BY_CATEGORY = "http://120.25.1.26:97/plant/getByCategory";
    public final static String UPLOAD_PLANT_PIC = "http://120.25.1.26/upload";
    public final static String GET_PLANTS_BY_NAME_LIST = "http://120.25.1.26:97/plant/getPlantsByList";
    public final static String GET_SEARCH_SUGGESTIONS_LIST = "http://120.25.1.26:97/plant/getSearchSuggestionsList";
    public final static String GET_PLANT_BY_SEARCH_PLACE = "http://120.25.1.26:97/plant/getPlantBySearchPlace";
    public final static String ADD_TO_COLLECTION = "http://120.25.1.26:97/plantCollection/addToCollection";
    public final static String REMOVE_COLLECTION = "http://120.25.1.26:97/plantCollection/removeCollection";
    public final static String GET_IF_COLLECTED = "http://120.25.1.26:97/plantCollection/getIfCollected";


    public static PlantService create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(PlantService.class);
    }

}
