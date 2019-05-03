package com.example.mydatastorageapplication;

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

public class ClientServerActivity extends AppCompatActivity {

    String url1 = "http://hunnyjain2792.000webhostapp.com/cdac/index.php";
    String url2 = "http://hunnyjain2792.000webhostapp.com/cdac/post.php";
    EditText name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_server);
        name = findViewById(R.id.editname);
        age = findViewById(R.id.editage);
    }

    public void show(View view) {
        final String s1 = name.getText().toString();
        final String s2 = age.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error + "", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", s1);
                params.put("age", s2);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ClientServerActivity.this);
        requestQueue.add(request);
    }
}
