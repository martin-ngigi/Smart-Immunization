package com.example.smart_immunization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button sendSmsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SOLVE THE NETWORK ERROR/BUG BY :
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);


        sendSmsBtn = findViewById(R.id.sendSmsBtn);

        sendSmsBtn.setOnClickListener( e-> {
            sendSms();
        });
    }

    private void sendSms() {
        String username = "";
        String apiKey = "";//africastalking.com
        AfricasTalking.initialize(username, apiKey);
        // Initialize a service e.g. SMS
        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
        // Use the service
        try {
            List<Recipient> response = sms.send("Hello its Martin ;-)!", new String[]{"+254797292290"}, true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}