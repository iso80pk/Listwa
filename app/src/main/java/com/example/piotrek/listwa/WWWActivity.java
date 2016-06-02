package com.example.piotrek.listwa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotrek.listwa.models.MyResponse;
import com.example.piotrek.listwa.network.InternetActivity;
import com.example.piotrek.listwa.network.MyResponseRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class WWWActivity extends InternetActivity {

    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PREFERENCES_TEXT_FIELD = "textField";
    private SharedPreferences preferences;

    TextView isConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_www);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        isConnected = (TextView) findViewById(R.id.isConnected);

        // check if you are connected or not
        if (isConnected()) {
            isConnected.setBackgroundColor(0xFF00CC00);
            isConnected.setText("Masz połączenie z siecią");
        } else {
            isConnected.setText("Nie masz połączenia z siecia!");
        }


    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void onL1(View view) {

        MyResponseRequest myResponseRequest = new MyResponseRequest(5,1);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });

    }

    public void offL1(View view) {

        MyResponseRequest myResponseRequest = new MyResponseRequest(5,0);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }

    public void onL2(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(16,1);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }

    public void offL2(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(16,0);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });

    }

    public void onL3(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(14,1);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });


    }
    public void offL3(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(14,0);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }

    public void onL4(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(12,1);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }


    public void offL4(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(12,0);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void onL5(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(13,1);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }

    public void offL5(View view) {
        MyResponseRequest myResponseRequest = new MyResponseRequest(13,0);
        spiceManager.execute(myResponseRequest, new RequestListener<MyResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                showToast("błąd: "+ spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(MyResponse myResponse) {
                showToast((String) myResponse.getMessage());
            }
        });
    }
}
