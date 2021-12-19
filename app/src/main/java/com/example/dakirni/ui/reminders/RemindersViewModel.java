package com.example.dakirni.ui.reminders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RemindersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RemindersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reminders fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}