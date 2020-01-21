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

    @GET("patra_project/all")
    Call<ResponseModel> AllProject(@Header("x-api-key") String xApiKey,
                                      @Header("x-token") String xToken,
                                      @Header("Content-Type") String contentType,
                                      @Query("uuid") String uuid);

    @GET("patra_marketing/all")
    Call<ResponseModel> AllMarketing(@Header("x-api-key") String xApiKey,
                                   @Header("x-token") String xToken,
                                   @Header("Content-Type") String contentType,
                                   @Query("uuid") String uuid);

    @GET("patra_side_supervisor/all")
    Call<ResponseModel> AllSiteSupervisor(@Header("x-api-key") String xApiKey,
                                   @Header("x-token") String xToken,
                                   @Header("Content-Type") String contentType,
                                   @Query("uuid") String uuid);

    @GET("patra_depot/all")
    Call<ResponseModel> AllDepot(@Header("x-api-key") String xApiKey,
                                   @Header("x-token") String xToken,
                                   @Header("Content-Type") String contentType,
                                   @Query("uuid") String uuid);

    @FormUrlEncoded
    @POST("patra_project/add")
    Call<ResponseModel> InputProject(@Header("x-api-key") String xApiKey,
                              @Header("x-token") String xToken,
                              @Header("Content-Type") String contentType,
                              @Field("nama_project") String nama_project);

    @FormUrlEncoded
    @POST("patra_marketing/add")
    Call<ResponseModel> InputMarketing(@Header("x-api-key") String xApiKey,
                                       @Header("x-token") String xToken,
                                       @Header("Content-Type") String contentType,
                                       @Field("nama_mor") String nama_mor,
                                       @Field("project_id") String project_id);

    @FormUrlEncoded
    @POST("patra_side_supervisor/add")
    Call<ResponseModel> InputSupervisor(@Header("x-api-key") String xApiKey,
                                       @Header("x-token") String xToken,
                                       @Header("Content-Type") String contentType,
                                       @Field("nama_ss") String nama_ss,
                                       @Field("marketing_id") String marketing_id);

    @FormUrlEncoded
    @POST("patra_depot/add")
    Call<ResponseModel> InputDepot(@Header("x-api-key") String xApiKey,
                                        @Header("x-token") String xToken,
                                        @Header("Content-Type") String contentType,
                                        @Field("nama_depot") String nama_depot,
                                        @Field("side_supervisor") String side_supervisor);

    @FormUrlEncoded
    @POST("patra_profile/add")
    Call<ResponseModel> EditData(@Header("x-api-key") String xApiKey,
                                 @Header("x-token") String xToken,
                                 @Header("Content-Type") String contentType,
                                 @Field("uuid") String uuid,
                                 @Field("email") String email,
                                 @Field("hp") String hp,
                                 @Field("ktp") String ktp,
                                 @Field("jabatan") String jabatan,
                                 @Field("nama") String nama,
                                 @Field("tanggal_lahir") String tanggal_lahir);
}
