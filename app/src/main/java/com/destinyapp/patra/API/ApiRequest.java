package com.destinyapp.patra.API;

import com.destinyapp.patra.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseModel> login(@Header("x-api-key") String xApiKey,
                                     @Header("Content-Type") String contentType,
                                     @Field("username") String username,
                                     @Field("password") String password);

    @GET("patra_profile/detail/")
    Call<ResponseModel> ProfileDetail(@Header("x-api-key") String xApiKey,
                                      @Header("x-token") String xToken,
                                      @Header("Content-Type") String contentType,
                                      @Query("uuid") String uuid);
}
