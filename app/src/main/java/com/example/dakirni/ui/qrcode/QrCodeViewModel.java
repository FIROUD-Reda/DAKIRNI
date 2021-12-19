package com.example.dakirni.ui.qrcode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QrCodeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public QrCodeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is qr Code fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}