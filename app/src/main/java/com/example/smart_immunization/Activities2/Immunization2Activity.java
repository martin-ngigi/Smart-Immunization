package com.example.smart_immunization.Activities2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_immunization.Activities.ImmunizationActivity;
import com.example.smart_immunization.R;

public class Immunization2Activity extends AppCompatActivity {

    //link https://my.clevelandclinic.org/health/articles/11288-childhood-immunization-schedule


    private Button btn_add_immnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immunization2);


        btn_add_immnBtn = findViewById(R.id.btn_add_immnBtn);

        btn_add_immnBtn.setOnClickListener( e -> {
            startActivity(new Intent(Immunization2Activity.this,AddImmunizationActivity.class));
        });
    }
}