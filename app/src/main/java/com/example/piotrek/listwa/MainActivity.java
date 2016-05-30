package com.example.piotrek.listwa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
