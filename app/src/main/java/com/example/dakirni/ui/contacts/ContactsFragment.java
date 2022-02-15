package com.example.dakirni.ui.contacts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dakirni.AdapterContact.AdapterContact;
import com.example.dakirni.AdapterContact.Contact;
import com.example.dakirni.AddContactActivity;
import com.example.dakirni.AddMessage;
import com.example.dakirni.R;
import com.example.dakirni.RetrofitInterface;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.databinding.ContactsFragmentBinding;
import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.example.dakirni.msgsAdapter.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsFragment extends Fragment {
    private ContactsFragmentBinding binding;
    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<Contact> contactsList = new ArrayList<>();
    AdapterContact adapter;
    environementVariablesOfDakirni env;
    private String BASE_URL = env.BASE_URL;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        binding = ContactsFragmentBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initData(root);
        View newMsgBtn = root.findViewById(R.id.floating_add_contact);
        newMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(root.getContext(), AddContactActivity.class);
                intent.putExtra("update", false);
                startActivity(intent);
            }
        });
        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        initData(root);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void initRecyclerView(View root, List<Contact> contactsList) {
        mrecyclerView = root.findViewById(R.id.contactRecycleView);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterContact(contactsList, getContext());
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void initData(View root) {
        SonDbHelper sonDbHelper = new SonDbHelper(root.getContext());
        ArrayList<String> arrayList = sonDbHelper.lireToken();
        StringBuffer maListe = new StringBuffer();
        try {
            Iterator<String> iter = arrayList.iterator();
            while (iter.hasNext()) {
                maListe.append(iter.next());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(root.getContext(), "Aucun Résultat trouvé !", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getContext(), maListe.toString(), Toast.LENGTH_LONG).show();
        Call<List<Contact>> call = retrofitInterface.getContacts(maListe.toString(), environementVariablesOfDakirni.key);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (response.body() != null && response.code() == 200) {
                    contactsList = new ArrayList<>();
                    for (Contact c : response.body()) {
                        contactsList.add(c);
                    }
                }
                initRecyclerView(root, contactsList);
            }
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(root.getContext(), "Fail : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}