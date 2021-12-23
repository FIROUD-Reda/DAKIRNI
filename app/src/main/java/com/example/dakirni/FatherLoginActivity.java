package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class FatherLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=new Intent(FatherLoginActivity.this,ParentsDashBoard.class);
        startActivity(intent);
    }
}