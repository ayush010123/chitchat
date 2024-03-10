package com.ise.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class Loginphonenumber extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phoneinput;
    Button sendotpbtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginphonenumber);

        countryCodePicker = findViewById(R.id.ccp_picker);
        phoneinput = findViewById(R.id.mobileno_edit);
        sendotpbtn = findViewById(R.id.sendotp_btn);
        progressBar = findViewById(R.id.loginphone_pb);
        progressBar.setVisibility(View.GONE);

        countryCodePicker.registerCarrierNumberEditText(phoneinput);
        sendotpbtn.setOnClickListener((v -> {
            if(!countryCodePicker.isValidFullNumber()){
                phoneinput.setError("Phone number not valid");
                return;
            }
            Intent intent = new Intent(Loginphonenumber.this,Loginotp.class);
            intent.putExtra("phone", countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        }));

    }
}