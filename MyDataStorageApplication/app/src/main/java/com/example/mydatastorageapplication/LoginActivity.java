package com.example.mydatastorageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button submit;
    final String URL = "https://cdac-2019.000webhostapp.com/cdac";
    String url2 = "http://hunnyjain2792.000webhostapp.com/cdac/post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login);
        password = findViewById(R.id.passw);
        submit = findViewById(R.id.submit);


    }

    public void login(View view) {
        final String strEmail = email.getText().toString();
        final String strPassw = password.getText().toString();
        if (strEmail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
        } else {
            if (strPassw.isEmpty()) {
                password.setError("Password is required");
                password.requestFocus();
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals(1)) {
                            Toast.makeText(getApplicationContext(), "Login done", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                            finish();
                        } else if (response.equals(0))
                            Toast.makeText(getApplicationContext(), "Login crash", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();

                        hashMap.put("email", strEmail);
                        hashMap.put("password", strPassw);
                        hashMap.put("choice", "2");
                        return hashMap;
                    }
                };
            }
        }
    }


    public void sigup(View view) {

    }

    public void profile(View view) {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
