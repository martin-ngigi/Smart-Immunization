package com.example.smart_immunization.Activities;
import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import com.android.volley.toolbox.HttpHeaderParser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.MainActivity;
import com.example.smart_immunization.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.android.volley.toolbox.HttpHeaderParser;

import dmax.dialog.SpotsDialog;
/**
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
 **/

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_fNameR, edt_lNameR, edt_email, edt_phone, edt_password;
    private Button btn_signup, btn_login;
    private AlertDialog alertDialog;
//    App app;

    private  String fname, lname, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
        Realm.init(this);
        app = new App(new AppConfiguration.Builder(Constants.appID)
                .build());
         **/

        edt_fNameR = findViewById(R.id.edt_fNameR);
        edt_lNameR = findViewById(R.id.edt_lNameR);
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
            validateData();
        });

        btn_login.setOnClickListener( e->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

    }

    private void validateData() {

        fname = edt_fNameR.getText().toString();
        lname = edt_lNameR.getText().toString();
        phone = edt_phone.getText().toString();
        email = edt_email.getText().toString();
        password = edt_password.getText().toString();

        // validating if the text field is empty or not.
        if (TextUtils.isEmpty(fname)){
            edt_fNameR.setError("First Name field is empty. Enter Name.");
            edt_fNameR.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(lname)){
            edt_lNameR.setError("Last Name field is empty. Enter Name.");
            edt_lNameR.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(phone)){
            edt_phone.setError("Phone field is empty. Enter Phone.");
            edt_phone.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(email)){
            edt_email.setError("Email field is empty. Enter Email.");
            edt_email.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(password)){
            edt_password.setError("Password field is empty. Enter Password.");
            edt_password.setFocusable(true);
            return;
        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            edt_email.setError("Email format is wrong. Enter Correct email.");
//            edt_email.setFocusable(true);
//            return;
//        }
        if (password.length()<8){
            edt_password.setError("Password cant be less than 8 characters. Enter Password again.");
            edt_password.setFocusable(true);
            return;
        }



        // calling a method to post the data and passing our name and job.
        postDataUsingVolley(fname, lname, phone, email, password);

    }


    private void postDataUsingVolley(String fname, String lname, String phone, String email, String password) {
        alertDialog.setTitle("Please wait...");
        alertDialog.setMessage("Sending registration data");
        alertDialog.show();

        // url to post our data
        //String url = "http://127.0.0.1:8000/auth/signup/";
        String BASE_URL = Constants.Base_Url;
        String url = BASE_URL+"/auth/signup/";


        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

        //post data
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //hide dialog
                alertDialog.dismiss();

                try {
                    //parsing the response to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    //extract data from json object response
                    String message = respObj.getString("message");
                    Toast.makeText(RegisterActivity.this, "Response Message: "+message, Toast.LENGTH_SHORT).show();

                    //login
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                catch (JSONException e){

                    Log.e("VolleyError", "onErrorResponse: "+e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "1. Error: An error occurred.\nHint: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hide dialog
                alertDialog.dismiss();
                //Handle errors
                //check for the response data in the VolleyError and parse it your self.
                onErrorResponse2(error);
                Log.e("VolleyError", "2. onErrorResponse: "+error.getMessage());
                Toast.makeText(RegisterActivity.this, "2. Error: Failed to get response. \nHint: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                //creating a map for storing our values in key and value pair.
                HashMap<String, String> params = new HashMap<>();
                //passing our key and value pair to our parameters.
                /**
                 * {
                 *     "email": "martinwainaina002@gmail.com",
                 *     "username": "wainaina2",
                 *     "phone": "0797292290",
                 *     "first_name": "Martin",
                 *     "last_name": "Wainaina",
                 *     "date_of_birth": "1999-11-23",
                 *     "password": "12345678"
                 * }
                 * **/
                params.put("email", email);
                params.put("username",email.replace("@gmail.com", ""));
                params.put("phone",phone);
                params.put("first_name",fname);
                params.put("last_name",lname);
                params.put("date_of_birth","");
                params.put("password",password);

                //return params
                return params;
            }
        };

        //make a json request
        queue.add(request);
    }

    //check for the response data in the VolleyError and parse it your self.
    // import com.android.volley.toolbox.HttpHeaderParser;
    public void onErrorResponse2(VolleyError error) {

        // As of f605da3 the following should work
        NetworkResponse response = error.networkResponse;
        if (error instanceof ServerError && response != null) {
            try {
                String res = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                // Now you can use any deserializer to make sense of data
                JSONObject obj = new JSONObject(res);
                Toast.makeText(this, "Object is : "+obj.toString(), Toast.LENGTH_SHORT).show();
                Log.i("RegisterActivity", "Object is "+obj);
            } catch (UnsupportedEncodingException e1) {
                // Couldn't properly decode data to string
                e1.printStackTrace();
                Log.e("RegisterActivity", "e1 onErrorResponse2:\nCouldn't properly decode data to string\n "+e1.getMessage());
            } catch (JSONException e2) {
                // returned data is not JSONObject?
                Log.e("RegisterActivity", "e2 onErrorResponse2: \nreturned data is not JSONObject?\n"+e2.getMessage());
                e2.printStackTrace();
            }
        }
    }


    /**
    private void postDataUsingVolley(String fname, String lname, String phone, String email, String password) {
        alertDialog.setTitle("Please wait...");
        alertDialog.setMessage("Sending registration data");
        alertDialog.show();

        // url to post our data
        //String url = "http://127.0.0.1:8000/auth/signup/";
        String BASE_URL = "https://ed4e-41-80-98-18.in.ngrok.io";
        String url = BASE_URL+"/auth/signup/";


        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

        //post data
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //hide dialog
                alertDialog.dismiss();

                try {
                    //parsing the response to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    //extract data from json object response
                    //String accessToken = respObj.getString("token");

                    //String dateJoined = respObj.getString("date_joined");
                    int id = respObj.getInt("id");
                    //Toast.makeText(RegisterActivity.this, "ID: "+id+"\nDate: "+dateJoined, Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this, "Response: "+response.toString(), Toast.LENGTH_SHORT).show();

                    //login
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();

                }
                catch (JSONException e){
                    //hide dialog
                    alertDialog.dismiss();

                    Log.e("VolleyError", "onErrorResponse: "+e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "1. Error: An error occurred.\nHint: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hide dialog
                alertDialog.dismiss();
                //Handle errors
                Log.e("VolleyError", "2. onErrorResponse: "+error.getMessage());
                Toast.makeText(RegisterActivity.this, "2. Error: Failed to get response. \nHint: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                //creating a map for storing our values in key and value pair.
                HashMap<String, String> params = new HashMap<>();
                //passing our key and value pair to our parameters.
                /**
                 * {
                 *     "email": "martinwainaina002@gmail.com",
                 *     "username": "wainaina2",
                 *     "phone": "0797292290",
                 *     "first_name": "Martin",
                 *     "last_name": "Wainaina",
                 *     "date_of_birth": "1999-11-23",
                 *     "password": "12345678"
                 * }

                params.put("email", email);
                params.put("username",email.replace("@gmail.com", ""));
                params.put("phone",phone);
                params.put("first_name",fname);
                params.put("last_name",lname);
                params.put("date_of_birth","");
                params.put("password",password);

                //return params
                return params;
            }
        };

        //make a json request
        queue.add(request);
    }
    **/

}