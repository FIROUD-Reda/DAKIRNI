package com.example.dakirni.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AddMessage;
import com.example.dakirni.R;
import com.example.dakirni.databinding.FragmentMessageBinding;
import com.example.dakirni.msgsAdapter.Message;
import com.example.dakirni.msgsAdapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageFragment extends Fragment {

    private FragmentMessageBinding binding;
    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<Message> msgsList;
    MessagesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMessageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initDataforson();
        initRecyclerView(root);
        View newMsgBtn= root.findViewById(R.id.floatingActionButton);
newMsgBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(root.getContext(), AddMessage.class);
        startActivity(intent);
    }
});
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecyclerView(View root) {
        mrecyclerView = root.findViewById(R.id.msgsListRecycleView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new MessagesAdapter(msgsList, getContext());
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initDataforson() {
        msgsList = new ArrayList<>();
        Message msg =new Message();
        msg.setMsgLabel("Hello");
        msg.setTextContent("hola senores");
        msg.setCreationDate(new Date());
        msgsList.add(msg);
//        msgsList.add(new Message("Titre1","Col", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre2","Content1sdkjskfsdkfsslkjvskdfhsdkjfnsdkjfnsdlfk,sdflkn,sv,xc;vns,fdvsklfdjnsdkjgnkjdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre3","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre4","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre5","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));
//        msgsList.add(new Message("Titre6","Content1sdkjskfsdkfsdhfjsdkbfhdsbfsd", new String[]{"sdfsdf"}, new String[]{"sfsdfsf"}));

    }


}