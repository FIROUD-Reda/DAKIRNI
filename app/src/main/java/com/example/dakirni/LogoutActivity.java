package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dakirni.database.son.SonDbHelper;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        SonDbHelper sonDbHelper = new SonDbHelper(LogoutActivity.this);
        sonDbHelper.deleteSon();

        Toast.makeText(getApplicationContext(), "Logout activity", Toast.LENGTH_SHORT).show();
    }
}