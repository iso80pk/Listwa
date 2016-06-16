package com.example.piotrek.listwa;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotrek.listwa.models.SetTimer;
import com.example.piotrek.listwa.network.InternetActivity;
import com.example.piotrek.listwa.network.SetTimerRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class Ustawienia extends InternetActivity {

    private EditText aREST_ID;
    private EditText time;
    private TextView localAddress;



    private Button btnSave;
    private static final String PREFERENCES_NAME = "myPreferences"; // ID dla aREST
    private static final String PREFERENCES_NAME1 = "myPreferences1"; // adres lokalny

    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private static final String PREFERENCES_TEXT_FIELD1 = "textField";

    private SharedPreferences preferences;
    private SharedPreferences preferences1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        preferences1 = getSharedPreferences(PREFERENCES_NAME1, Activity.MODE_PRIVATE);


        aREST_ID = (EditText) findViewById(R.id.editText);
        localAddress = (TextView) findViewById(R.id.editText4);
        time= (EditText) findViewById(R.id.editTextTime);



        btnSave = (Button) findViewById(R.id.button);
        initButtonOnClick();
        restoreData();

    }

    private void initButtonOnClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
                showToast("Zapisano ustawienia");
            }
        });
    }

    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        String editTextData = aREST_ID.getText().toString();

        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);

        preferencesEditor.commit();

    }

    private void restoreData() {
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        aREST_ID.setText(textFromPreferences);
        String textFromPreferences1 = preferences1.getString(PREFERENCES_TEXT_FIELD1, "");
        localAddress.setText(textFromPreferences1);

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void ustawCzas(View view) {
        String timeFromGUI =time.getText().toString();

        if(timeFromGUI.matches("")){
            showToast("Podaj czas!");

        }
        else {
            SetTimerRequest setTimerRequest = new SetTimerRequest(Integer.parseInt( timeFromGUI));
            spiceManager.execute(setTimerRequest, new RequestListener<SetTimer>() {

                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    showToast("błąd: " + spiceException.getMessage());
                }

                @Override
                public void onRequestSuccess(SetTimer setTimer) {
                    showToast("Ustawiono czas na "+setTimer.getReturn_value().toString() +" s.");

                }
            });
        }



    }
}
