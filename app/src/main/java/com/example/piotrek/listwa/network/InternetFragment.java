package com.example.piotrek.listwa.network;


import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class InternetFragment extends Fragment {
    protected SpiceManager spiceManager = new SpiceManager(ApiService.class);


    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted())
        {
            spiceManager.shouldStop();
        }
        super.onStop();
    }
}
