package com.pccw.beaconbank.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.beaconbank.R;
import com.pccw.beaconbank.dto.UserDTO;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.activity_login_email)TextInputLayout email;
    @BindView(R.id.activity_login_password)TextInputLayout password;

    UserDTO userDTO = new UserDTO();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void login(View view) throws JSONException{
        email.setError(null);
        password.setError(null);

        if(email.getEditText().getText().toString().isEmpty()){
            email.setError("Please enter your Email Address");
        }else if(password.getEditText().getText().toString().isEmpty()){
            password.setError("Please enter your Password");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString()).matches()){
            email.setError("Invalid Email Address");
        }else{
            userDTO.setEmail(email.getEditText().getText().toString());
            userDTO.setPassword(password.getEditText().getText().toString());
            sendCredentials();
            progressDialog.show();
        }

    }

    private void sendCredentials() throws JSONException {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/login", userDTO.getUserObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response Object", String.valueOf(response));
                try {
                    validate(response);
                    progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("Volley Error", error.getMessage());
                if(error.getMessage().contains("End of input at character 0 of")){
                    email.setError("Invalid Email Address");
                    password.setError("Invalid Password");
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> params = new HashMap<>();
                params.put("type", "mobile");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void validate(JSONObject response) throws IOException {
        UserDTO userDTO = new ObjectMapper().readValue(response.toString(), UserDTO.class);
        if(!this.userDTO.getPassword().equals(userDTO.getPassword())){
            email.setError("Invalid Email Address");
            password.setError("Invalid Password");
        }else{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
