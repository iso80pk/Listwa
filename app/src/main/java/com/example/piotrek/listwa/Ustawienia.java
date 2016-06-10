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

public class Ustawienia extends AppCompatActivity {

    private EditText aREST_ID;
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
}
