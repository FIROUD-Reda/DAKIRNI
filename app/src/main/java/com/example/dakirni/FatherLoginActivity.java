package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dakirni.AdapterFather.ModelClass;
import com.example.dakirni.AdapterFather.MyAdapter;
import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.AdapterSon.ModelClassforson;

import java.util.List;

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