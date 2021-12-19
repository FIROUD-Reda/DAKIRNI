package com.example.dakirni.ui.homeboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeBoardViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public HomeBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is DashBoard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }}