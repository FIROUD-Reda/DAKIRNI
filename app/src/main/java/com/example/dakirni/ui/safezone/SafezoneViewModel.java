package com.example.dakirni.ui.safezone;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SafezoneViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SafezoneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is safezone fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}