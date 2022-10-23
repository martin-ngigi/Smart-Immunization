package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.smart_immunization.R;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout immuneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        immuneLayout = findViewById(R.id.immuneLayout);

        immuneLayout.setOnClickListener(e ->{
            startActivity(new Intent(HomeActivity.this, ImmunizationActivity.class));
        });
    }
}