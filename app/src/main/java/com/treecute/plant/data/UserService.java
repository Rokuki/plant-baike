package com.treecute.plant.data;

import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;

import java.util.Map;

import io.reactivex.Observable;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by mkind on 2017/11/24 0024.
 */

public interface UserService {

    @FormUrlEncoded
    @POST Observable<IntegerResponse> signUp(@Url String url, @FieldMap Map<String,String> data);

    @FormUrlEncoded
    @POST Observable<ResponseResult<User>> login(@Url String url, @FieldMap Map<String,String> data);
}
