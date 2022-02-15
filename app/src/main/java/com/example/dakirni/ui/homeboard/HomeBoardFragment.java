package com.example.dakirni.ui.homeboard;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dakirni.R;
import com.example.dakirni.databinding.FragmentHomeBoardBinding;
import com.example.dakirni.environements.environementVariablesOfDakirni;


public class HomeBoardFragment extends Fragment {
    TextView name, key;
    ImageView image;
    private HomeBoardViewModel homeBoardViewModel;
    private FragmentHomeBoardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeBoardViewModel =
                new ViewModelProvider(this).get(HomeBoardViewModel.class);

        binding = FragmentHomeBoardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        name = root.findViewById(R.id.username_input7);
        key = root.findViewById(R.id.username_input4);
        image = root.findViewById(R.id.roundedImageView);
        name.setText(environementVariablesOfDakirni.name);
        key.setText(environementVariablesOfDakirni.key);

        image.setImageBitmap(decodeImage(environementVariablesOfDakirni.image));


//        final TextView textView = binding.textHomeboard;
//        homeBoardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private Bitmap decodeImage(String stringedImage) {
        byte[] bytes = Base64.decode(stringedImage, Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        // set bitmap on imageView
        return bitmap;
    }

}