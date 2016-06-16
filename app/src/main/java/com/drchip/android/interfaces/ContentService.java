package com.drchip.android.interfaces;

import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.Url;

/**
 * Created by mohann on 30-04-2016.
 */
public interface ContentService {

    @POST
    Call<JsonObject> helloWorld(@Url String url, @Body JsonObject attendEventRequest);

    @GET
    Call<JsonObject> helloWorld(@Url String url);

    @FormUrlEncoded
    @POST
    Call<JsonObject> sendMail(@Url String url, @Field("name") String name, @Field("email") String email,
                              @Field("phone") String phone, @Field("message") String message);

}
