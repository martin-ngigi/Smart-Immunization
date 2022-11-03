package com.example.smart_immunization.Activities2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

public class Schedules2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules2);

        //send sms
        String messageTxt = "Hello "+ Constants.email +"\nUpcoming Health events are: 1. Polio vaccination(KIIC@09:00) 2.Covid19 vaccination(AfyaCenter@09:00) ";
        MySms2 mySms =  new MySms2();
        mySms.sendSms(messageTxt);
    }
}