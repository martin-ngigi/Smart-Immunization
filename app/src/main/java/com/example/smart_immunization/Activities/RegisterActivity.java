package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.Toast;


import com.example.smart_immunization.Constants;
import com.example.smart_immunization.MainActivity;
import com.example.smart_immunization.R;

import java.util.concurrent.atomic.AtomicReference;

import dmax.dialog.SpotsDialog;
/**
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
 **/

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_name, edt_email, edt_phone, edt_password;
    private Button btn_signup, btn_login;
    private AlertDialog alertDialog;
//    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
        Realm.init(this);
        app = new App(new AppConfiguration.Builder(Constants.appID)
                .build());
         **/

        edt_name = findViewById(R.id.edt_nameR);
        edt_email = findViewById(R.id.edt_emailR);
        edt_phone = findViewById(R.id.edt_phoneR);
        edt_password = findViewById(R.id.edt_passwordR);
        btn_signup = findViewById(R.id.btn_signupR);
        btn_login = findViewById(R.id.btn_loginR);

        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Please wait")
                .setCancelable(false)
                .setTheme(R.style.DialogCustomTheme)
                .build();

        btn_signup.setOnClickListener( e->{
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
        });

        btn_login.setOnClickListener( e->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

    }

    /**
    private void createAccount() {
        alertDialog.show();

        //get data from ui
        String name = edt_name.getText().toString();
        String phone = edt_phone.getText().toString();
        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();

        app.getEmailPassword().registerUserAsync(email, password, it -> {
            if (it.isSuccess()){
                //success register user
                alertDialog.dismiss();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);

            }
            else {
                //Failed to register user
                alertDialog.dismiss();
                Log.e("AUTH", ""+it.getError().toString());
                Toast.makeText(this, ""+it.getError().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
     **/
}