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
import com.example.dakirni.database.father.FatherDbHelper;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.databinding.FragmentMessageBinding;
import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.example.dakirni.msgsAdapter.Message;
import com.example.dakirni.msgsAdapter.MessagesAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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
    private String BASE_URL = environementVariablesOfDakirni.backEndUrl;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        binding = FragmentMessageBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initDataforson(root);
        msgsList.add(new Message("Welcome to our main page","Green", "this is it", new Date(), "hi", "ho", false, false, false,"falseKey"));
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
        SonDbHelper sonDbHelper = new SonDbHelper(root.getContext());
        ArrayList<String> arrayList = sonDbHelper.lireToken();
        StringBuffer maListe = new StringBuffer();

        try {
            Iterator<String> iter = arrayList.iterator();
            while (iter.hasNext()) {
                maListe.append(iter.next());
            }
            Toast.makeText(root.getContext(),maListe.toString(),Toast.LENGTH_SHORT).show();
        }catch (ArrayIndexOutOfBoundsException e){
            Toast.makeText(root.getContext(),"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
        }

        Call<List<Message>> call = retrofitInterface.getMessages(maListe.toString(),environementVariablesOfDakirni.key);

        call.enqueue(new Callback<List<Message>>() {

            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                if (response.body() != null) {
                    msgsList = new ArrayList<>();
                    for (Message gottenmessages : response.body()) {
                        msgsList.add(gottenmessages);
                    }
                }
                initRecyclerView(root, msgsList);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(root.getContext(), "We have sadly encountred an error while getting the messages: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}