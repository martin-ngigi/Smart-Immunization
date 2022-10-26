package com.example.smart_immunization.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_emailL, edt_passwordL;
    private Button btn_loginL,btn_signupL;

    private String email, password;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        });

        btn_signupL.setOnClickListener( e->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

    }

    private void validateData() {

        email = edt_emailL.getText().toString();
        password = edt_emailL.getText().toString();

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
        String url = BASE_URL+"/auth/login/";

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        //post data
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //hide dialog
                dialog.dismiss();

                try {
                    //parsing the response to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    //extract data from the json object response
                    String message = respObj.getString("message");
                    //String access =  respObj.getString("access");
                    Toast.makeText(LoginActivity.this, "access: "+message, Toast.LENGTH_SHORT).show();
                    //proceed to home
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                catch (JSONException e){
                    //hide dialogue
                    dialog.dismiss();

                    Log.e("LoginActivity", "onResponse: error: "+e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "onResponse: error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hide dialogue
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "VolleyError: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                //creating a map for storing values in keys and value pair
                Map<String, String> params = new HashMap<>();
                //passing our key and value pair to our parameters
                /**
                 * {
                 *     "email": "martinwainaina002@gmail.com",
                 *     "password": "12345678"
                 * }
                 * **/

                params.put("email", edt_emailL.getText().toString());
                params.put("password", edt_passwordL.getText().toString());

                return params;
            }
        };

        //make a queue to json request
        queue.add(request);
    }
}