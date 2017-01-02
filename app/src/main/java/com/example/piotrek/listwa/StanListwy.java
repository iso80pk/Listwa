package com.example.piotrek.listwa;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.piotrek.listwa.models.DeviceStatus;
import com.example.piotrek.listwa.models.MyResponse;
import com.example.piotrek.listwa.network.DeviceStatusRequest;
import com.example.piotrek.listwa.network.InternetActivity;
import com.example.piotrek.listwa.network.MyResponseRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.IOException;

//AppCompatActivity
public class StanListwy extends InternetActivity {

    TextView isConnected;
    Switch D5;
    Switch D16;
    Switch D14;
    Switch D12;
    Switch D13;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stan_listwy);


        isConnected = (TextView) findViewById(R.id.isConnected);

        D5 = (Switch) findViewById(R.id.switchD5);
        D16 = (Switch) findViewById(R.id.switchD16);
        D14 = (Switch) findViewById(R.id.switchD14);
        D12 = (Switch) findViewById(R.id.switchD12);
        D13 = (Switch) findViewById(R.id.switchD13);


        // check if you are connected or not
        if (isConnected()) {
            isConnected.setBackgroundColor(0xFF00CC00);
            isConnected.setText("Masz połączenie z siecią");
        } else {
            isConnected.setText("Nie masz połączenia z siecia!");
        }

        DeviceStatusRequest deviceStatusRequest = new DeviceStatusRequest();
        spiceManager.execute(deviceStatusRequest, new RequestListener<DeviceStatus>() {


            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: " + spiceException.getMessage());
                setListenersForSwitches();
               // setOnclickSwitchesAction();
            }

            @Override
            public void onRequestSuccess(DeviceStatus deviceStatus) {
                D5.setChecked(deviceStatus.isD5());
                D16.setChecked(deviceStatus.isD16());
                D14.setChecked(deviceStatus.isD14());
                D12.setChecked(deviceStatus.isD12());
                D13.setChecked(deviceStatus.isD13());
                showToast("Pobrano aktualny stan przełączników ?");
                setListenersForSwitches();
               // setOnclickSwitchesAction();

            }
        });





        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }




    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "StanListwy Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.piotrek.listwa/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "StanListwy Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.piotrek.listwa/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void setListenersForSwitches (){
        D5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isConnected()) {
                    if (isChecked) {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(5, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(5, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    }
                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }


        });

        D16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isConnected()) {
                    if (isChecked) {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(16, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(16, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    }
                }
                else{
                    showToast("Brak połączenia z Internetem");
                }

            }
        });

        D14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isConnected()) {
                    if(isChecked){
                        MyResponseRequest myResponseRequest = new MyResponseRequest(14, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(14, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    }


                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }
        });
        D12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isConnected()) {
                    if(isChecked){
                        MyResponseRequest myResponseRequest = new MyResponseRequest(12, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(12, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    }



                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }
        });
        D13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isConnected()) {
                    if(isChecked){
                        MyResponseRequest myResponseRequest = new MyResponseRequest(13, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(13, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    }
                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }
        });

    }


    private void setOnclickSwitchesAction(){
    D5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()) {
                    if (D5.isChecked()) {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(5, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(5, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    }
                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }

        });

        D16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()) {
                    if (D16.isChecked()) {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(16, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(16, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }

                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast(myResponse.getMessage());
                            }
                        });
                    }
                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }

        });


        D14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()) {
                    if(D14.isChecked()){
                        MyResponseRequest myResponseRequest = new MyResponseRequest(14, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(14, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    }


                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }

        });

        D12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()) {
                    if(D12.isChecked()){
                        MyResponseRequest myResponseRequest = new MyResponseRequest(12, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(12, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    }



                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }

        });

        D13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()) {
                    if(D13.isChecked()){
                        MyResponseRequest myResponseRequest = new MyResponseRequest(13, 1);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    } else {
                        MyResponseRequest myResponseRequest = new MyResponseRequest(13, 0);
                        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
                            @Override
                            public void onRequestFailure(SpiceException spiceException) {
                                showToast("błąd: " + spiceException.getMessage());
                            }
                            @Override
                            public void onRequestSuccess(MyResponse myResponse) {
                                showToast((String) myResponse.getMessage());
                            }
                        });
                    }
                }
                else{
                    showToast("Brak połączenia z Internetem");
                }
            }

        });



    }
}
