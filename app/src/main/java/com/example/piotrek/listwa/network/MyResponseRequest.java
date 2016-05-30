package com.example.piotrek.listwa.network;

import com.example.piotrek.listwa.models.MyResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import retrofit.client.Response;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class MyResponseRequest extends RetrofitSpiceRequest <MyResponse,ApiInterface>{

    private int nr;
    private int stare;


    public MyResponseRequest(int nr,int stare) {
        super(MyResponse.class,ApiInterface.class);
        this.nr = nr;
        this.stare = stare;
    }

    @Override

    public MyResponse loadDataFromNetwork() throws Exception {
        return getService().myResponse(nr,stare);
    }

}
