package com.example.dakirni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterFather.ModelClass;
import com.example.dakirni.AdapterFather.MyAdapter;

import java.util.ArrayList;
import java.util.List;



public class List_son extends AppCompatActivity {

    RecyclerView sonrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> sonList;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_father);
        initData();
        initRecyclerView();
    }
    private void initRecyclerView() {
        sonrecyclerView=findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        sonrecyclerView.setLayoutManager(layoutManager);
        adapter=new MyAdapter(sonList, this);
        sonrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
    private void initData() {
        sonList = new ArrayList<>();

        sonList.add(new ModelClass(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));





    }


}