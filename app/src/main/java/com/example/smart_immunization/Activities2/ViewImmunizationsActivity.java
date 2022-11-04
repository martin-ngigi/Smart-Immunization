package com.example.smart_immunization.Activities2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_immunization.Adapters.AdapterImmunizations;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.Models.Immunizations;
import com.example.smart_immunization.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewImmunizationsActivity extends AppCompatActivity {

    private RecyclerView idRVImmunizations;
    private AdapterImmunizations adapterImmunizations;
    private ArrayList<Immunizations> immunizationsArrayList;
    private String url = Constants.Base_Url+"/users/"+Constants.id+"/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_immunizations);

        idRVImmunizations = findViewById(R.id.idRVImmunizations);

        immunizationsArrayList = new ArrayList<>();
        getData();
        
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(ViewImmunizationsActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("immunizations");
                    if (jsonArray.length() > 0){
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            int id = jsonObject2.getInt("id");
                            String immunizationName = jsonObject2.getString("immunizationName");
                            String immunizationDosageLevel = jsonObject2.getString("immunizationDosageLevel");
                            String immunizationDate = jsonObject2.getString("immunizationDate");
                            String nextImmunizationDate = jsonObject2.getString("nextImmunizationDate");
                            String administeredDate = jsonObject2.getString("administeredDate");

                            Immunizations immunizations = new Immunizations(id, immunizationName, immunizationDosageLevel, immunizationDate, nextImmunizationDate, administeredDate);

                            immunizationsArrayList.add(immunizations);

                            buildRecyclerView();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("ViewImmunizations", "onResponse: Error"+e.getMessage());
                    Toast.makeText(ViewImmunizationsActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("ViewImmunizations", "onResponse: Error"+error.getMessage());
                Toast.makeText(ViewImmunizationsActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapterImmunizations = new AdapterImmunizations(immunizationsArrayList, ViewImmunizationsActivity.this);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        idRVImmunizations.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        idRVImmunizations.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        idRVImmunizations.setAdapter(adapterImmunizations);
    }
}