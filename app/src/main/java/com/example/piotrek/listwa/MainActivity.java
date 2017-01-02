package com.example.piotrek.listwa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.piotrek.listwa.models.DigitalResponse;
import com.example.piotrek.listwa.network.DigitalResponseRequest;
import com.example.piotrek.listwa.network.InternetActivity;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MainActivity extends InternetActivity {

    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String ACTUAL_URL_KEY = "ACTUAL_URL";
    private static final String AREST_CLIENT_NUMBER_KEY = "AREST_CLIENT_NUMBER"; //aREST ID
    private static final String LOCAL_ESP_ADDRESS_KEY = "LOCAL_ESP_ADDRESS"; //ESP address in local network

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME,Activity.MODE_PRIVATE);
        String urlUsedForCommunication ="https://cloud.arest.io/"+sharedPreferences.getString(AREST_CLIENT_NUMBER_KEY, "889785849");  //make correct url with aREST ID

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACTUAL_URL_KEY,urlUsedForCommunication);
        editor.commit();


        DigitalResponseRequest digitalResponseRequest = new DigitalResponseRequest();
        spiceManager.execute(digitalResponseRequest, new RequestListener<DigitalResponse>() {

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("Problem z komunikacja Internetową" );

                //set local address as current address
                String localAddress= sharedPreferences.getString(LOCAL_ESP_ADDRESS_KEY,"");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(ACTUAL_URL_KEY,localAddress);
                editor.commit();


            }

            @Override
            public void onRequestSuccess(DigitalResponse digitalResponse) {
                if(digitalResponse.getConnected() == null){
                    showToast("Nie skonfigurowano aREST"); //or ESP module is not connected ?
                }
                else if(digitalResponse.getConnected()){

                    // We have an Internet connection

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(LOCAL_ESP_ADDRESS_KEY, digitalResponse.getMessage().toString());
                    editor.commit();

                }
                else {
                    //we don't have an Internet connection

                    //set local address as current address
                    String localAddress= sharedPreferences.getString(LOCAL_ESP_ADDRESS_KEY,"");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(ACTUAL_URL_KEY,localAddress);
                    editor.commit();


                    showToast("Moduł ESP nie ma połączenia z Internetem");
                }


            }


        });



    }

    public void stan(View view) {
        Intent intent =new Intent(this,StanListwy.class);
        startActivity(intent);
    }

    public void ustawienia(View view) {
        Intent intent =new Intent(this,Ustawienia.class);
        startActivity(intent);
    }

    public void oprojekcie(View view) {
        Intent intent =new Intent(this,OProjekcie.class);
        startActivity(intent);
    }

    public void onasa(View view) {
        Intent intent =new Intent(this,ONas.class);
        startActivity(intent);
    }
    public void www(View view) {
        Intent intent =new Intent(this,WWWActivity.class);
        startActivity(intent);
    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
