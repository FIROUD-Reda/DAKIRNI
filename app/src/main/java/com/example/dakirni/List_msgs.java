package com.example.dakirni;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.AdapterSon.ModelClassforson;
import com.example.dakirni.msgsAdapter.Message;
import com.example.dakirni.msgsAdapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.List;

public class List_msgs extends AppCompatActivity {

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<Message> msgsList;
    MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msgs_list);
        //initData();

        initDataforson();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mrecyclerView = findViewById(R.id.msgsListRecycleView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new MessagesAdapter(msgsList, this);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initDataforson() {
        msgsList = new ArrayList<>();
        msgsList.add(new Message("Titre1","Content1sdkjskfs", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
        msgsList.add(new Message("Titre2","Content1sdkjskfsdkfsslkjvskdfhsdkjfnsdkjfnsdlfk,sdflkn,sv,xc;vns,fdvsklfdjnsdkjgnkjdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
        msgsList.add(new Message("Titre3","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
        msgsList.add(new Message("Titre4","Content1sdkjkjrsgdfghbfshsddfkjssssssnbjhdfsghskdfjghdsfbgshjdfsgdjfskdfbdfshdghdfskljbgfdsjfjfjkfjdffjfjhskfsdkfsdhfjsdkbfhdsbfsdsfskg,fdlkg,df;gndfjgndf;gnfhskjdgnkjsdjfglsdjguherztkghdsfkjghsdkjfghsdkjfghkdsjfhgkdsfhgkldfshgdkfsjghsdkfghskdfghdksjfghkdfsjghkjdsfghksdfjghskdjdkfjsgfsghkjdfshgjkdfshgkjdsfhgjkdsfbnjkdsfgkjhdfskghdkfsjghdkjfshgkjsjdfhgkj", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
        msgsList.add(new Message("Titre5","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
        msgsList.add(new Message("Titre6","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));

    }
}
