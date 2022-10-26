package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.LinearLayout;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.example.smart_immunization.Activities.MySms;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout immuneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //SOLVE THE NETWORK ERROR/BUG BY :
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);


        //send sms
        String messageTxt = "Hello "+ Constants.email +"! \nWelcome to Smart-Immunization. Smart immunization is a digital form of educating the less privileged people such as the illiterate, the old and those in rural areas regarding immunization schedules, how they are carried out and after how long.";
        MySms mySms =  new MySms();
        mySms.sendSms(messageTxt);

        immuneLayout = findViewById(R.id.immuneLayout);

        immuneLayout.setOnClickListener(e ->{
            startActivity(new Intent(HomeActivity.this, ImmunizationActivity.class));
        });
    }

}