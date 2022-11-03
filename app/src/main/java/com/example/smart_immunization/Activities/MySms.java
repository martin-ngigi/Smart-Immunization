package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.LinearLayout;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

import java.io.IOException;
import java.util.List;

public class MySms {
    //send sms
    void sendSms(String message) {

        //SOLVE THE NETWORK ERROR/BUG BY :
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        String username = "immuno";
        String apiKey = "daf34abdb82da422d49c9c3b5fe1083d13cd47fc811766924f33601d706a3b7a";//africastalking.com
        AfricasTalking.initialize(username, apiKey);
        // Initialize a service e.g. SMS
        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
        // Use the service
        try {
            List<Recipient> response = sms.send(""+message, new String[]{""+Constants.phone}, true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
