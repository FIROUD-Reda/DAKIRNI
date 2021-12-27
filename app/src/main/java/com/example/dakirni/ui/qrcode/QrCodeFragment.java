package com.example.dakirni.ui.qrcode;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.print.PrintHelper;

import com.example.dakirni.R;
import com.example.dakirni.databinding.FragmentQrCodeBinding;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrCodeFragment extends Fragment {
String global_String="nabil lamkadam";
String global_KEY="auth father";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ImageView qrImage;

    private Button generateQrBtn,imprimerQrbtn;
    Bitmap bitmap,bittemp=null;
    QRGEncoder qrgEncoder;
    private QrCodeViewModel qrCodeViewModel;
    private FragmentQrCodeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        qrCodeViewModel =
                new ViewModelProvider(this).get(QrCodeViewModel.class);

        binding = FragmentQrCodeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        qrImage = root.findViewById(R.id.idQR);
        generateQrBtn =root.findViewById(R.id.buttonqr);
        imprimerQrbtn=root.findViewById(R.id.ImprimerQR);
        imprimerQrbtn.setVisibility(View.INVISIBLE);
        generateQrBtn.setVisibility(View.INVISIBLE);
        radioGroup=root.findViewById(R.id.Group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group,@IdRes int checkedId) {
                radioButton=root.findViewById(checkedId);
                qrImage.setImageBitmap(bittemp);
                switch(radioButton.getId()){
                    case R.id.AUTHENTIFICATION:{
                        generateQrBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                createQR(global_KEY);
                            }
                        });
                        generateQrBtn.setVisibility(View.VISIBLE);
                     imprimerQrbtn.setVisibility(View.INVISIBLE);
                    }
                    break;
                    case R.id.generateQR:{
                        generateQrBtn.setVisibility(View.VISIBLE);
                        imprimerQrbtn.setVisibility(View.VISIBLE);

                        generateQrBtn.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                createQR(global_String);
                            }
                        });
                    }
                    break;

                }

            }
        });
        imprimerQrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    PrintHelper photoPrinter = new PrintHelper(getActivity());
                    photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

                    photoPrinter.printBitmap("droids.jpg - test print", bitmap);
                }

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void createQR (String arg)
    {

        WindowManager manager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;
        qrgEncoder = new QRGEncoder(arg, null, QRGContents.Type.TEXT, dimen);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.e("Tag", e.toString());
        }
    }
    }
