package com.example.smart_immunization.Activities2;

import android.os.StrictMode;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.example.smart_immunization.Constants;

import java.io.IOException;
import java.util.List;

public class MySms2 {
    //send sms
    void sendSms(String message) {

        //SOLVE THE NETWORK ERROR/BUG BY :
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        String username = "";
        String apiKey = "";//africastalking.com
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
