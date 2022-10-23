package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.smart_immunization.MainActivity;
import com.example.smart_immunization.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_emailL, edt_passwordL;
    private Button btn_loginL,btn_signupL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_emailL = findViewById(R.id.edt_emailL);
        edt_passwordL = findViewById(R.id.edt_passwordL);
        btn_loginL = findViewById(R.id.btn_loginL);
        btn_signupL = findViewById(R.id.btn_signupL);

        btn_loginL.setOnClickListener( e->{
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

        btn_signupL.setOnClickListener( e->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

    }
}