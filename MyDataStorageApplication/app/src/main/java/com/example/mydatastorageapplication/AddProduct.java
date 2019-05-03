package com.example.mydatastorageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AddProduct extends AppCompatActivity {
    final String URL = "https://cdac-2019.000webhostapp.com/cdac/";
    EditText name, price, description;
    String strName, strprice, strDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        name = findViewById(R.id.editname);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
    }

    public void addProduct(View view) {
        strName = name.getText().toString();
        strprice = price.getText().toString();
        strDescription = description.getText().toString();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RRRRRRR", response);
                if (response.equals("1")) {
                    Toast.makeText(getApplicationContext(), "Login done", Toast.LENGTH_LONG).show();
                    finish();
                } else if (response.equals("0"))
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

                hashMap.put("name", strName);
                hashMap.put("price", strprice);
                hashMap.put("description", strDescription);
                hashMap.put("choice", "3");
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddProduct.this);
        requestQueue.add(stringRequest);

    }
}
