package com.example.smart_immunization.Activities2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Login2Activity extends AppCompatActivity {

    private EditText edt_emailL, edt_passwordL;
    private Button btn_loginL,btn_signupL;

    private String email, password;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        edt_emailL = findViewById(R.id.edt_emailL);
        edt_passwordL = findViewById(R.id.edt_passwordL);
        btn_loginL = findViewById(R.id.btn_loginL);
        btn_signupL = findViewById(R.id.btn_signupL);

        dialog =new SpotsDialog.Builder()
                .setCancelable(false)
                .setContext(this)
                .setTheme(R.style.DialogCustomTheme)
                .setMessage("Please wait")
                .build();

        btn_loginL.setOnClickListener( e->{
            validateData();
            //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            String email = edt_emailL.getText().toString().replace("@gmail.com", "");
            Constants.email = email;
        });
        btn_signupL.setOnClickListener( e->{
            startActivity(new Intent(Login2Activity.this, Register2Activity.class));
        });

    }

    private void validateData() {

        email = edt_emailL.getText().toString();
        password = edt_passwordL.getText().toString();

        // validating if the text field is empty or not.
        if (TextUtils.isEmpty(email)){
            edt_emailL.setError("Email field is empty. Enter Email.");
            edt_emailL.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(password)){
            edt_passwordL.setError("Password field is empty. Enter Password.");
            edt_passwordL.setFocusable(true);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_emailL.setError("Email format is wrong. Enter Correct email.");
            edt_emailL.setFocusable(true);
            return;
        }
        if (password.length()<8){
            edt_passwordL.setError("Password cant be less than 8 characters. Enter Password again.");
            edt_passwordL.setFocusable(true);
            return;
        }



        // calling a method to post the data and passing our name and job.
        postDataUsingVolley( email, password);

    }

    private void postDataUsingVolley(String email, String password) {
        dialog.show();

        String BASE_URL = Constants.Base_Url;
        String url = BASE_URL+"/user-by-email-or-phone/?search="+email;

        RequestQueue queue = Volley.newRequestQueue(Login2Activity.this);

        //post data
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //hide dialog
                dialog.dismiss();

                //Toast.makeText(Login2Activity.this, "Response: "+response.toString(), Toast.LENGTH_SHORT).show();


                try {
                    //JSONObject respObj = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(response);

                    if (jsonArray.length() > 0){
                        JSONObject firstObject = jsonArray.getJSONObject(0);
                        String email1 = firstObject.getString("email");
                        String password1 = firstObject.getString("password");
                        String firstName1 = firstObject.getString("firstName");
                        String lastName1 = firstObject.getString("lastName");
                        String phone1 = firstObject.getString("phone");
                        String username1 = firstObject.getString("username");
                        String age1 = firstObject.getString("age");
                        String nationId1 = firstObject.getString("nationId");
                        String county1 = firstObject.getString("county");

                        //set data
                        Constants.email= email1;
                        Constants.phone = phone1;
                        Constants.last_name = lastName1;
                        Constants.user_name = username1;
                        Constants.age = age1;
                        Constants.nationalId = nationId1;
                        Constants.county = county1;
                        Constants.first_name = firstName1;



                        Toast.makeText(Login2Activity.this, "email: "+email1+ "\npassword: "+password1, Toast.LENGTH_LONG).show();

                        if (email.equals(email1) && password.equals(password1)){
                            //proceed to home
                            Intent intent = new Intent(Login2Activity.this, Home2Activity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Login2Activity.this, "Wrong Login credentials", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
                catch (JSONException e){
                    //hide dialogue
                    dialog.dismiss();

                    Log.e("LoginActivity", "onResponse: error: "+e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(Login2Activity.this, "onResponse: error", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hide dialogue
                dialog.dismiss();
                Toast.makeText(Login2Activity.this, "VolleyError: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //make a queue to json request
        queue.add(request);
    }
}