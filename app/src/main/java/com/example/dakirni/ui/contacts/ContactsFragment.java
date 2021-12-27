package com.example.dakirni.ui.contacts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dakirni.AdapterContact.AdapterContact;
import com.example.dakirni.AdapterContact.ModelClassforContact;
import com.example.dakirni.AdapterFather.ModelClass;
import com.example.dakirni.AdapterFather.MyAdapter;
import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.R;
import com.example.dakirni.databinding.ContactsFragmentBinding;
import com.example.dakirni.ui.homeboard.HomeBoardViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    private ContactsFragmentBinding binding;
    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClassforContact> contactList;
    List<ModelClassforContact> contactsList;

    AdapterContact adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        binding = ContactsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initData();
        initRecyclerView(root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initRecyclerView(View root) {
        mrecyclerView = root.findViewById(R.id.contactRecycleView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterContact(contactsList, getContext());
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void initData() {
        contactsList = new ArrayList<>();

        contactsList.add(new ModelClassforContact(R.drawable.bo,"ismail","0665478912","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.girl,"othmane","0854789221","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        contactsList.add(new ModelClassforContact(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));





    }


}