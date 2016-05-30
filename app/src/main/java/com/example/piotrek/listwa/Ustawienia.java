package com.example.piotrek.listwa;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ustawienia extends AppCompatActivity {

    private EditText etToSave;
    private EditText etToSave1;
    private EditText etToSave2;

    private Button btnSave;
    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_NAME1 = "myPreferences1";
    private static final String PREFERENCES_NAME2 = "myPreferences2";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private static final String PREFERENCES_TEXT_FIELD1 = "textField";
    private static final String PREFERENCES_TEXT_FIELD2 = "textField";
    private SharedPreferences preferences;
    private SharedPreferences preferences1;
    private SharedPreferences preferences2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        preferences1 = getSharedPreferences(PREFERENCES_NAME1, Activity.MODE_PRIVATE);
        preferences2 = getSharedPreferences(PREFERENCES_NAME2, Activity.MODE_PRIVATE);

        etToSave = (EditText) findViewById(R.id.editText);
        etToSave1 = (EditText) findViewById(R.id.editText4);
        etToSave2 = (EditText) findViewById(R.id.editText3);

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
        SharedPreferences.Editor preferencesEditor1 = preferences1.edit();
        SharedPreferences.Editor preferencesEditor2 = preferences2.edit();
        String editTextData = etToSave.getText().toString();
        String editTextData1 = etToSave1.getText().toString();
        String editTextData2 = etToSave2.getText().toString();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
        preferencesEditor1.putString(PREFERENCES_TEXT_FIELD1, editTextData1);
        preferencesEditor2.putString(PREFERENCES_TEXT_FIELD2, editTextData2);
        preferencesEditor.commit();
        preferencesEditor1.commit();
        preferencesEditor2.commit();
    }

    private void restoreData() {
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        etToSave.setText(textFromPreferences);
        String textFromPreferences1 = preferences1.getString(PREFERENCES_TEXT_FIELD1, "");
        etToSave1.setText(textFromPreferences1);
        String textFromPreferences2 = preferences2.getString(PREFERENCES_TEXT_FIELD2, "");
        etToSave2.setText(textFromPreferences2);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
