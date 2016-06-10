package com.example.piotrek.listwa.network;

import com.example.piotrek.listwa.models.DeviceStatus;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Piotrek on 10.06.2016.
 */
public class DeviceStatusRequest  extends RetrofitSpiceRequest<DeviceStatus,ApiInterface> {

     public DeviceStatusRequest() {
        super(DeviceStatus.class, ApiInterface.class);
    }


    @Override
    public DeviceStatus loadDataFromNetwork() throws Exception {
        return getService().deviceStatus();
    }
}
