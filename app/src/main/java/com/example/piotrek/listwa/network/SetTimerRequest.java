package com.example.piotrek.listwa.network;

import com.example.piotrek.listwa.models.SetTimer;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Piotrek on 10.06.2016.
 */
public class SetTimerRequest extends RetrofitSpiceRequest<SetTimer,ApiInterface> {

    private Integer time;

    public SetTimerRequest(Integer time) {
        super(SetTimer.class,ApiInterface.class);
        this.time = time;
    }


    @Override
    public SetTimer loadDataFromNetwork() throws Exception {
        return getService().setTimer(time);
    }
}