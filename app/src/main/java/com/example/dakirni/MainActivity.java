package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dakirni.AdapterFather.ModelClass;
import com.example.dakirni.AdapterFather.MyAdapter;
import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.AdapterSon.ModelClassforson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    //this is a function that starts the father activity intent it is basicly used here with the text view:Father and the image View :Father
    public void startFatherActivity(View view) {
        Intent intent=new Intent(MainActivity.this,FatherLoginActivity.class);
        startActivity(intent);
    }
    //this is a function that starts the son activity intent it is basicly used here with the text view:Son and the image View :Son
    public void startSonActivity(View view) {
        Intent intent=new Intent(MainActivity.this,SonLoginActivity.class);
        startActivity(intent);
    }
}