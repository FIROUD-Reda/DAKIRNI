package com.example.dakirni.ui.qrcode;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dakirni.R;
import com.example.dakirni.databinding.FragmentMessageBinding;
import com.example.dakirni.databinding.FragmentQrCodeBinding;
import com.example.dakirni.ui.message.MessageViewModel;

public class QrCodeFragment extends Fragment {

    private QrCodeViewModel qrCodeViewModel;
    private FragmentQrCodeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        qrCodeViewModel =
                new ViewModelProvider(this).get(QrCodeViewModel.class);

        binding = FragmentQrCodeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textQrcode;
        qrCodeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}