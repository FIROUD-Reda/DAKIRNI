package com.example.dakirni.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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