package com.example.dakirni.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dakirni.MainActivity;
import com.example.dakirni.R;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.databinding.FragmentMessageBinding;
import com.example.dakirni.databinding.LougoutFragmentBinding;

public class LougoutFragment extends Fragment {
    private LougoutFragmentBinding binding;

    private LougoutViewModel mViewModel;
    View root;

    public static LougoutFragment newInstance() {
        return new LougoutFragment();
    }
  ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LougoutFragmentBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        Toast.makeText(root.getContext(), "Logout for you !", Toast.LENGTH_SHORT).show();

        SonDbHelper sonDbHelper = new SonDbHelper(root.getContext());
        sonDbHelper.deleteSon();
        Intent intent = new Intent(root.getContext(), MainActivity.class);

        startActivity(intent);



        return inflater.inflate(R.layout.lougout_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LougoutViewModel.class);

        // TODO: Use the ViewModel
    }

}