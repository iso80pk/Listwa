package com.example.piotrek.listwa.network;

import android.app.Activity;
import android.content.SharedPreferences;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class ApiService  extends RetrofitGsonSpiceService {

    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String ACTUAL_URL_KEY = "ACTUAL_URL";
    private SharedPreferences sharedPreferences;



    private final static String BASE_URL = "https://cloud.arest.io/889785849";
   // private final static String BASE_URL = "";
    //889785849

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ApiInterface.class);
    }

    @Override
    protected String getServerUrl() {
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME,Activity.MODE_PRIVATE);
        return sharedPreferences.getString(ACTUAL_URL_KEY,"");
    }

}