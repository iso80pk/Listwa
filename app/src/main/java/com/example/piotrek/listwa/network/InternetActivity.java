package com.example.piotrek.listwa.network;

import android.support.v7.app.ActionBarActivity;

import com.octo.android.robospice.SpiceManager;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class InternetActivity extends ActionBarActivity{
    protected SpiceManager spiceManager = new SpiceManager(ApiService.class);


    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}
