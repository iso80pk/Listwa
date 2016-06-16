package com.example.piotrek.listwa.network;

import com.example.piotrek.listwa.models.SetTimer;
import com.example.piotrek.listwa.models.DeviceStatus;
import com.example.piotrek.listwa.models.DigitalResponse;
import com.example.piotrek.listwa.models.MyResponse;

import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Piotrek on 15.04.2016.
 */
public interface ApiInterface {
    @GET("/digital/{nr}/{state}")
    MyResponse myResponse(@Path("nr") int nr, @Path("state") int state);

    @GET("/info/")
    DigitalResponse digitalResponse();

    @GET("/list")
    DeviceStatus deviceStatus();

    @GET("/timer")
    SetTimer setTimer(@Query("params") Integer time);






}
