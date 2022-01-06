package com.example.dakirni.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AddMessage;
import com.example.dakirni.R;
import com.example.dakirni.RetrofitInterface;
import com.example.dakirni.databinding.FragmentMessageBinding;
import com.example.dakirni.msgsAdapter.Message;
import com.example.dakirni.msgsAdapter.MessagesAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageFragment extends Fragment {

    private FragmentMessageBinding binding;
    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<Message> msgsList = new ArrayList<>();

    MessagesAdapter adapter;
    private String BASE_URL = "http:/192.168.26.9:3000";
    //private String BASE_URL = "http://10.0.2.2:3000";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initDataforson(root);
        msgsList.add(new Message("Welcome to our assi", "this is it", new Date(), "hi", "ho", false, false, false));
//        initRecyclerView(root);
        View newMsgBtn = root.findViewById(R.id.floatingActionButton);
        newMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root.getContext(), AddMessage.class);
                startActivity(intent);
            }
        });
        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        initDataforson(root);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecyclerView(View root, List<Message> msgsList) {
        mrecyclerView = root.findViewById(R.id.msgsListRecycleView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new MessagesAdapter(msgsList, getContext());
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initDataforson(View root) {

        Call<List<Message>> call = retrofitInterface.getMessages();
        call.enqueue(new Callback<List<Message>>() {

            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                if (response.body() != null) {
                    msgsList = new ArrayList<>();
                    for (Message a : response.body()) {
                        msgsList.add(a);

                    }
                }
                initRecyclerView(root, msgsList);

//                msgsList.set(0, response.body().get(4));
//                msgsList=response.body();

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(root.getContext(), "hello" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

//        msgsList.add(new Message("Titre1","Col", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre2","Content1sdkjskfsdkfsslkjvskdfhsdkjfnsdkjfnsdlfk,sdflkn,sv,xc;vns,fdvsklfdjnsdkjgnkjdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre3","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre4","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre5","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre6","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));

    }


}