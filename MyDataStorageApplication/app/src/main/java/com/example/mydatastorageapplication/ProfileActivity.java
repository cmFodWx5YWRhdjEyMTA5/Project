package com.example.mydatastorageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void newProduct(View view) {
        Intent intent = new Intent(ProfileActivity.this, AddProduct.class);
        startActivity(intent);
    }
    public void showProduct(View view) {
        Intent intent = new Intent(ProfileActivity.this, ShowProduct.class);
        startActivity(intent);
    }
}
