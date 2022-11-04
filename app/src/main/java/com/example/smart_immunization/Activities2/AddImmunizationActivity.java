package com.example.smart_immunization.Activities2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.DatePicker;
import com.example.smart_immunization.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class AddImmunizationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView imnNameEdt, dosageLevelEdt, nextImnDateTv;
    private Button AddBtn;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_immunization);

        imnNameEdt = findViewById(R.id.imnNameEdt);
        dosageLevelEdt =findViewById(R.id.dosageLevelEdt);
        nextImnDateTv = findViewById(R.id.nextImnDateTv);
        AddBtn = findViewById(R.id.AddBtn);

        dialog =new SpotsDialog.Builder()
                .setCancelable(false)
                .setContext(this)
                .setTheme(R.style.DialogCustomTheme)
                .setMessage("Saving data. Please wait...")
                .build();


        nextImnDateTv.setOnClickListener( e->{
            DatePicker datePicker = new DatePicker();
            datePicker.show(getSupportFragmentManager(), "DATE PICK");
        });

        AddBtn.setOnClickListener(e -> {
            validateData();
        });
    }

    private void validateData() {
        String immunizationName = imnNameEdt.getText().toString();
        String dosageLevel  =dosageLevelEdt.getText().toString();
        String nextImmunization = nextImnDateTv.getText().toString();

        // validating if the text field is empty or not.
        if (TextUtils.isEmpty(immunizationName)){
            imnNameEdt.setError("immunizationName field is empty. Enter immunizationName.");
            imnNameEdt.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(dosageLevel)){
            dosageLevelEdt.setError("dosageLevel field is empty. Enter dosageLevel.");
            dosageLevelEdt.setFocusable(true);
            return;
        }
        if (nextImmunization.equals("Next Immunization Date")){
            nextImnDateTv.setText("Enter a valid date.");
            return;
        }

        saveDataToDb(immunizationName, dosageLevel, nextImmunization);

    }

    private void saveDataToDb(String immunizationName, String dosageLevel, String nextImmunization) {
        dialog.show();

        String BaseUrl = Constants.Base_Url;
        String url = BaseUrl+"/immunizations/";

        RequestQueue queue =  Volley.newRequestQueue(AddImmunizationActivity.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String imnName = jsonObject.getString("immunizationName");
                    Toast.makeText(AddImmunizationActivity.this, imnName+" added Successfully", Toast.LENGTH_SHORT).show();

                    //clear data
                    imnNameEdt.setText("");
                    dosageLevelEdt.setText("");
                    nextImnDateTv.setText("Select Date");
                }
                catch (JSONException e){
                    Toast.makeText(AddImmunizationActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("AddImmunizationActivity", "onResponse Error: "+e.getMessage() );

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddImmunizationActivity.this, "Error2: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("AddImmunizationActivity", "onResponse Error2: "+error.getMessage() );

            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                HashMap<String, String> hashMap = new HashMap<>();

                //get date
                Calendar mCalendar = Calendar.getInstance();
                SimpleDateFormat simpleFormatDate = new SimpleDateFormat("dd_MM_yyyy");
                String dateTime = simpleFormatDate.format(mCalendar.getTime()).toString();

                /**
                 * {
                 *     "immunizationName": "Jonson",
                 *     "immunizationDosageLevel": "2nd1",
                 *     "immunizationDate": "03-11-2021",
                 *     "nextImmunizationDate": "03-12-2021",
                 *     "user": 1
                 *
                 * }
                 */
                hashMap.put("immunizationName",immunizationName);
                hashMap.put("immunizationDosageLevel", dosageLevel);
                hashMap.put("immunizationDate", ""+dateTime);
                hashMap.put("nextImmunizationDate", nextImmunization);
                hashMap.put("user", Constants.id+"");

                return  hashMap;

            }
        };

        queue.add(request);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String selectDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());

        SimpleDateFormat simpleFormatDate = new SimpleDateFormat("dd-MM-yyyy");
        String dateTime = simpleFormatDate.format(mCalendar.getTime()).toString();


        //set Dates
        nextImnDateTv.setText(dateTime);

    }
}