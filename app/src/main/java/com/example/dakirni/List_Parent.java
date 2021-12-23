package com.example.dakirni;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.AdapterSon.ModelClassforson;

import java.util.ArrayList;
import java.util.List;

public class List_Parent extends AppCompatActivity {

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassforson> fatherList;
    AdapterSon adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_father);
        //initData();

        initDataforson();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mrecyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterSon(fatherList, this);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initDataforson() {
        fatherList = new ArrayList<>();

        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));
        fatherList.add(new ModelClassforson(R.drawable.bo, "Nabil", "kEY:" + "eyJhbGciOiJIUzUxMiJ9", "_______________________________________"));


    }
}
