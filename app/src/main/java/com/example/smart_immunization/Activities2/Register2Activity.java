package com.example.smart_immunization.Activities2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_immunization.Constants;
import com.example.smart_immunization.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;


public class Register2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edt_fNameR, edt_lNameR, edt_email, edt_phone,
            edt_password, edt_ageR, edt_nationIdR, edt_countyR, edt_usernameR;
    private Button btn_signup, btn_login;
    private AlertDialog alertDialog;
    private Spinner genderSpinner;

    String[] gendersOptions = {"M", "F" };
//    App app;

    private  String fname, lname, email, phone, age, nationalId, county, username, password, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        edt_fNameR = findViewById(R.id.edt_fNameR);
        edt_lNameR = findViewById(R.id.edt_lNameR);
        edt_email = findViewById(R.id.edt_emailR);
        edt_phone = findViewById(R.id.edt_phoneR);
        edt_password = findViewById(R.id.edt_passwordR);
        btn_signup = findViewById(R.id.btn_signupR);
        btn_login = findViewById(R.id.btn_loginR);
        edt_ageR = findViewById(R.id.edt_ageR);
        edt_nationIdR = findViewById(R.id.edt_nationIdR);
        edt_countyR = findViewById(R.id.edt_countyR);
        edt_usernameR= findViewById(R.id.edt_usernameR);

        setSelectedGender();

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
            startActivity(new Intent(Register2Activity.this, Login2Activity.class));
        });

    }

    private void setSelectedGender() {
        // on below line we are initializing spinner with ids.
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);

        // on below line we are initializing adapter for our spinner
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gendersOptions);

        // on below line we are setting drop down view resource for our adapter.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // on below line we are setting adapter for spinner.
        genderSpinner.setAdapter(adapter);

        // on below line we are adding click listener for our spinner
        genderSpinner.setOnItemSelectedListener(this);

        // on below line we are creating a variable to which we have to set our spinner item selected.
        String selection = "M";

        // on below line we are getting the position of the item by the item name in our adapter.
        int spinnerPosition = adapter.getPosition(selection);

        // on below line we are setting selection for our spinner to spinner position.
        genderSpinner.setSelection(spinnerPosition);
    }

    private void validateData() {

        fname = edt_fNameR.getText().toString();
        lname = edt_lNameR.getText().toString();
        phone = edt_phone.getText().toString();
        email = edt_email.getText().toString();
        age = edt_ageR.getText().toString();
        nationalId = edt_nationIdR.getText().toString();
        county = edt_countyR.getText().toString();
        username = edt_usernameR.getText().toString();
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
        if (TextUtils.isEmpty(email)){
            edt_email.setError("Email field is empty. Enter Email.");
            edt_email.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(phone)){
            edt_phone.setError("Phone field is empty. Enter Phone.");
            edt_phone.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(age)){
            edt_ageR.setError("Age field is empty. Enter Age.");
            edt_ageR.setFocusable(true);
            return;
        }

        if (TextUtils.isEmpty(nationalId)){
            edt_nationIdR.setError("National Id field is empty. Enter National Id.");
            edt_nationIdR.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(county)){
            edt_countyR.setError("County field is empty. Enter County.");
            edt_countyR.setFocusable(true);
            return;
        }
        if (TextUtils.isEmpty(username)){
            edt_usernameR.setError("Username field is empty. Enter Username.");
            edt_usernameR.setFocusable(true);
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
        postDataUsingVolley(fname, lname, phone, email, age, nationalId, county, username, password);

    }


    private void postDataUsingVolley(String fname, String lname, String phone, String email, String age, String nationalId, String county, String username, String password) {
        alertDialog.setTitle("Please wait...");
        alertDialog.setMessage("Sending registration data");
        alertDialog.show();

        // url to post our data
        //String url = "http://127.0.0.1:8000/user2/";
        //String BASE_URL = Constants.Base_Url;
        //String url = BASE_URL+"/user2/";
        String url = "https://e9f5-41-80-97-89.in.ngrok.io/user2/";


        RequestQueue queue = Volley.newRequestQueue(Register2Activity.this);

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
                    String message = respObj.getString("email");
                    Toast.makeText(Register2Activity.this, "Response Message: "+message, Toast.LENGTH_SHORT).show();

                    //login
                    Intent intent = new Intent(Register2Activity.this, Home2Activity.class);
                    startActivity(intent);
                    finish();

                }
                catch (JSONException e){

                    Log.e("VolleyError", "onErrorResponse: "+e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(Register2Activity.this, "1. Error: An error occurred.\nHint: "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Register2Activity.this, "2. Error: Failed to get response. \nHint: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                //creating a map for storing our values in key and value pair.
                HashMap<String, String> params = new HashMap<>();
                //passing our key and value pair to our parameters.
                /**
                 {
                 "firstName": "Daniel",
                 "lastName": "Njoroge",
                 "email": "daniel@gmail.com",
                 "phone": "079729234",
                 "password": "12345",
                 "username": "daniel",
                 "gender":"M",
                 "age":"24",
                 "nationId": "48973722",
                 "county":"Nairobi"
                 }
                 * **/

                params.put("firstName", fname);
                params.put("lastName", lname);
                params.put("email",email);
                params.put("phone", phone);
                params.put("password", password);
                params.put("username",username);
                params.put("gender", gender);
                params.put("age", age);
                params.put("nationId", nationalId);
                params.put("county",county);

//                params.put("firstName", "Aer");
//                params.put("lastName", "Bdfg");
//                params.put("email","a@gmail.com");
//                params.put("phone", "071345");
//                params.put("password", "15678");
//                params.put("username","afgh");
//                params.put("gender", "M");
//                params.put("age", "23");
//                params.put("nationId", "123456");
//                params.put("county","Kiambu");

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        gender = "" + gendersOptions[position];
        Toast.makeText(Register2Activity.this, "" + gendersOptions[position] + " Selected..", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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