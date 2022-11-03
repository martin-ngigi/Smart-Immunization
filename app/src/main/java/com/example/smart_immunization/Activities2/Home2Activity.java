package com.example.smart_immunization.Activities2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

public class Home2Activity extends AppCompatActivity {

    private LinearLayout immuneLayout, upcomingLayout;

    private TextView nameTvH, phoneTvH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //get views
        nameTvH = findViewById(R.id.nameTvH);
        phoneTvH = findViewById(R.id.phoneTvH);
        immuneLayout = findViewById(R.id.immuneLayout);
        upcomingLayout = findViewById(R.id.upcomingLayout);

        nameTvH.setText(""+Constants.email.replace("@gmail.com", ""));
        phoneTvH.setText(""+Constants.phone);

        //send sms
        String messageTxt = "Hello "+ Constants.email +"! \nWelcome to Smart-Immunization. Smart immunization is a digital form of educating the less privileged people such as the illiterate, the old and those in rural areas regarding immunization schedules, how they are carried out and after how long.";
        MySms2 mySms =  new MySms2();
        mySms.sendSms(messageTxt);


        immuneLayout.setOnClickListener(e ->{
            startActivity(new Intent(Home2Activity.this, Immunization2Activity.class));
        });

        upcomingLayout.setOnClickListener(e ->{
            startActivity(new Intent(Home2Activity.this, Schedules2Activity.class));
        });
    }

}