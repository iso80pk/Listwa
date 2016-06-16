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


    private static final String PREFERENCES_NAME1 = "myPreferences1";
    private SharedPreferences preferences1; //local addres

    private static final String PREFERENCES_ActualURL= "ActualURL";
    private SharedPreferences ActualURL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferences1 = getSharedPreferences(PREFERENCES_NAME1, Activity.MODE_PRIVATE);
        ActualURL =getSharedPreferences(PREFERENCES_ActualURL, Activity.MODE_PRIVATE);

        // set URL
        SharedPreferences aREST_ID = getSharedPreferences("myPreferences", Activity.MODE_PRIVATE);
        String URL ="https://cloud.arest.io/"+aREST_ID.getString("textField", "");

        SharedPreferences.Editor preferencesEditorURL = ActualURL.edit();
        preferencesEditorURL.putString("Dupa",  URL);
        preferencesEditorURL.commit();

        DigitalResponseRequest digitalResponseRequest = new DigitalResponseRequest();
        spiceManager.execute(digitalResponseRequest, new RequestListener<DigitalResponse>() {

            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("Problem z komunikacja Internetową" );

                String URL =preferences1.getString("textField", ""); // lokalny
                //+ http:/
                SharedPreferences.Editor preferencesEditorURL = ActualURL.edit();
                preferencesEditorURL.putString("Dupa",URL);
                preferencesEditorURL.commit();

                //if(spiceException.getCause().)


            }

            @Override
            public void onRequestSuccess(DigitalResponse digitalResponse) {
                if(digitalResponse.getConnected()){

                    SharedPreferences aREST_ID = getSharedPreferences("myPreferences", Activity.MODE_PRIVATE);
                    String URL ="https://cloud.arest.io/"+aREST_ID.getString("textField", "");

                    SharedPreferences.Editor preferencesEditorURL = ActualURL.edit();
                    preferencesEditorURL.putString("Dupa",  URL);
                    preferencesEditorURL.commit();

                    SharedPreferences.Editor preferencesEditor1 = preferences1.edit();
                    preferencesEditor1.putString("textField",  digitalResponse.getMessage().toString());
                    preferencesEditor1.commit();

                }
                else {
                    //URL do komunikacji
                    String URL =preferences1.getString("textField", "");
                    //+ http:/
                    SharedPreferences.Editor preferencesEditorURL = ActualURL.edit();
                    preferencesEditorURL.putString("Dupa",  URL);
                    preferencesEditorURL.commit();

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
