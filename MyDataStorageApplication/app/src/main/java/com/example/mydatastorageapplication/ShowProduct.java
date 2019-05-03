package com.example.mydatastorageapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowProduct extends AppCompatActivity {
    String URL = "https://cdac-2019.000webhostapp.com/cdac/";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        listView = findViewById(R.id.list);

        showData();

    }

    private void showData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RRRRRRR", response);
                if (response.equals("0")) {
//                    Toast.makeText(getApplicationContext(), "Login done", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            strings.add(jsonObject.getString("name"));
                        }
                        ArrayAdapter<String>  arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,strings);
                        listView.setAdapter(arrayAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                    Toast.makeText(getApplicationContext(), "Login crash", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();

//                hashMap.put("name", strName);
//                hashMap.put("price", strprice);
//                hashMap.put("description", strDescription);
                hashMap.put("choice", "4");
                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShowProduct.this);
        requestQueue.add(stringRequest);

    }
}
