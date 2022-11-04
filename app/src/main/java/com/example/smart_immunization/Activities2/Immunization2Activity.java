package com.example.smart_immunization.Activities2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_immunization.Activities.ImmunizationActivity;
import com.example.smart_immunization.Adapters.AdapterImmunizations;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.Models.Immunizations;
import com.example.smart_immunization.R;

import java.util.ArrayList;

public class Immunization2Activity extends AppCompatActivity {

    //link https://my.clevelandclinic.org/health/articles/11288-childhood-immunization-schedule


    private Button btn_add_immnBtn, btn_view_imnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immunization2);


        btn_add_immnBtn = findViewById(R.id.btn_add_immnBtn);
        btn_view_imnBtn = findViewById(R.id.btn_view_imnBtn);


        btn_add_immnBtn.setOnClickListener( e -> {
            startActivity(new Intent(Immunization2Activity.this,AddImmunizationActivity.class));
        });

        btn_view_imnBtn.setOnClickListener( e -> {
            startActivity(new Intent(Immunization2Activity.this,ViewImmunizationsActivity.class));
        });
    }
}