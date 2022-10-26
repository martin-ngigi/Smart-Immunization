package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

public class SchedulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        //send sms
        String messageTxt = "Hello "+ Constants.email +"\nUpcoming Health events are: 1. Polio vaccination(KIIC@09:00) 2.Covid19 vaccination(AfyaCenter@09:00) ";
        com.example.smart_immunization.Activities.MySms mySms =  new com.example.smart_immunization.Activities.MySms();
        mySms.sendSms(messageTxt);
    }
}