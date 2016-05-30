package com.example.piotrek.listwa.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class ApiService  extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "https://cloud.arest.io/889785849";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ApiInterface.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

}