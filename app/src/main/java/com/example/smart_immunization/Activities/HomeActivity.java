package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.example.smart_immunization.Activities.MySms;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout immuneLayout, upcomingLayout;

    private TextView nameTvH, phoneTvH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get views
        nameTvH = findViewById(R.id.nameTvH);
        phoneTvH = findViewById(R.id.phoneTvH);
        immuneLayout = findViewById(R.id.immuneLayout);
        upcomingLayout = findViewById(R.id.upcomingLayout);

        nameTvH.setText(""+Constants.email.replace("@gmail.com", ""));
        phoneTvH.setText(""+Constants.phone);

        //send sms
        String messageTxt = "Hello "+ Constants.email +"! \nWelcome to Smart-Immunization. Smart immunization is a digital form of educating the less privileged people such as the illiterate, the old and those in rural areas regarding immunization schedules, how they are carried out and after how long.";
        MySms mySms =  new MySms();
        mySms.sendSms(messageTxt);


        immuneLayout.setOnClickListener(e ->{
            startActivity(new Intent(HomeActivity.this, ImmunizationActivity.class));
        });

        upcomingLayout.setOnClickListener(e ->{
            startActivity(new Intent(HomeActivity.this, SchedulesActivity.class));
        });
    }

}