package com.destinyapp.patra.API;

import com.destinyapp.patra.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseModel> login(@Header("x-api-key") String xApiKey,
                                     @Header("Content-Type") String contentType,
                                     @Field("username") String username,
                                     @Field("password") String password);
}
