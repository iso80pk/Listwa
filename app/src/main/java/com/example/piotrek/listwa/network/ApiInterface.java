package com.example.piotrek.listwa.network;

import com.example.piotrek.listwa.models.DeviceStatus;
import com.example.piotrek.listwa.models.DigitalResponse;
import com.example.piotrek.listwa.models.MyResponse;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Piotrek on 15.05.2016.
 */
public interface ApiInterface {
    @GET("/digital/{nr}/{state}")
    MyResponse myResponse(@Path("nr") int nr, @Path("state") int state);

    @GET("/digital/")
    DigitalResponse digitalResponse();

    @GET("/list")
    DeviceStatus deviceStatus();

    @GET("timer?params={time}")
    MyResponse myResponse(@Path("time") int time);



}
