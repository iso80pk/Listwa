package com.example.piotrek.listwa.network;

import com.example.piotrek.listwa.models.DigitalResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Piotrek on 03.06.2016.
 */
public class DigitalResponseRequest extends RetrofitSpiceRequest<DigitalResponse,ApiInterface> {


    public DigitalResponseRequest() {
        super(DigitalResponse.class,ApiInterface.class);
    }

    @Override
    public DigitalResponse loadDataFromNetwork() throws Exception {
        return getService().digitalResponse();
    }
}
