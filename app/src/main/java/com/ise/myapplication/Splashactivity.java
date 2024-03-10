package com.ise.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ise.myapplication.utils.firebase;

public class Splashactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        int delayMillis = 1000;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebase.isLoggedIn()){
                    startActivity(new Intent(Splashactivity.this,MainActivity.class));
                }
                else {
                    startActivity(new Intent(Splashactivity.this, Loginphonenumber.class));
                }
                finish();
            }
        },delayMillis);
    }
}