package com.example.piotrek.listwa;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.piotrek.listwa.models.MyResponse;
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


    private String string;


    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_NAME1 = "myPreferences1";
    private static final String PREFERENCES_NAME2 = "myPreferences2";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private static final String PREFERENCES_TEXT_FIELD1 = "textField";
    private static final String PREFERENCES_TEXT_FIELD2 = "textField";
    private SharedPreferences preferences;
    private SharedPreferences preferences1;
    private SharedPreferences preferences2;

    TextView isConnected;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stan_listwy);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        preferences1 = getSharedPreferences(PREFERENCES_NAME1, Activity.MODE_PRIVATE);
        preferences2 = getSharedPreferences(PREFERENCES_NAME2, Activity.MODE_PRIVATE);


        isConnected = (TextView) findViewById(R.id.isConnected);

        // check if you are connected or not
        if (isConnected()) {
            isConnected.setBackgroundColor(0xFF00CC00);
            isConnected.setText("Masz połączenie z siecią");
        } else {
            isConnected.setText("Nie masz połączenia z siecia!");
        }
        // add click listener to Button "POST"


        string = "";
        string += "IP: ";
        string += preferences.getString(PREFERENCES_TEXT_FIELD, "");
        string += "\nNazwa: ";
        string += preferences1.getString(PREFERENCES_TEXT_FIELD1, "");
        string += "\nHasło: ";
        string += preferences2.getString(PREFERENCES_TEXT_FIELD2, "");
        EditText editText = (EditText) findViewById(R.id.editText2);
        editText.setText(string);


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

    public void jsonclick(View view) throws IOException {

        TextView myAwesomeTextView = (TextView) findViewById(R.id.textView8);
        myAwesomeTextView.setText("Klikłeśś");


        MyResponseRequest myResponseRequest = new MyResponseRequest(5,0);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd..."+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });



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
}
