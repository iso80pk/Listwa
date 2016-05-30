package com.example.piotrek.listwa.network;

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

}
