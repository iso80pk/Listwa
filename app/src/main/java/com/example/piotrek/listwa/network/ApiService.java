package com.example.piotrek.listwa.network;

import android.app.Activity;
import android.content.SharedPreferences;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class ApiService  extends RetrofitGsonSpiceService {



    private final static String BASE_URL = "https://cloud.arest.io/889785849";
    //889785849

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ApiInterface.class);
    }

    @Override
    protected String getServerUrl() {
        //SharedPreferences preferences = getSharedPreferences("ActualURL",0);
        return BASE_URL;// preferences.getString("Dupa", "dupa.com");
    }

}