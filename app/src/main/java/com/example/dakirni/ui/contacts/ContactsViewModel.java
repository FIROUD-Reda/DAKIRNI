package com.example.dakirni.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterContact.ModelClassforContact;
import com.example.dakirni.AdapterFather.ModelClass;
import com.example.dakirni.AdapterFather.MyAdapter;
import com.example.dakirni.AdapterSon.AdapterSon;
import com.example.dakirni.AdapterSon.ModelClassforson;
import com.example.dakirni.R;

import java.util.ArrayList;
import java.util.List;

public class ContactsViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;


    public ContactsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Contact fragment !");

    }

    public LiveData<String> getText() {
        return mText;
    }


}