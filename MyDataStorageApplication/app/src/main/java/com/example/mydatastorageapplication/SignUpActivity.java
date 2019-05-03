package com.example.mydatastorageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    final String URL = "cdac-2019.000webhostapp.com/cdac";

    EditText editname, editemail, editpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editname = findViewById(R.id.editname);
        editemail = findViewById(R.id.editemail);
        editpassword = findViewById(R.id.editpassword);
    }

    public void newUser(View view){
        Intent inten = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(inten);
    }

    public void submit(View view) {
        final String name = editname.getText().toString();
        final String email = editemail.getText().toString();
        final String password = editpassword.getText().toString();
        Toast.makeText(getApplicationContext(), "Singup done", Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("1"))
                    Toast.makeText(getApplicationContext(), "Singup done", Toast.LENGTH_LONG).show();
                else if (response.equals("0"))
                    Toast.makeText(getApplicationContext(), "Singup crash", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name", name);
                hashMap.put("email", email);
                hashMap.put("password", password);
                hashMap.put("choice", "1");
                return hashMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
        requestQueue.add(stringRequest);
    }
}
