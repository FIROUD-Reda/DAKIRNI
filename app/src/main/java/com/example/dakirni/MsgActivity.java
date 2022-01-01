package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MsgActivity extends AppCompatActivity {
    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        Intent intent=getIntent();
        String label=intent.getStringExtra("Label");
        String context=intent.getStringExtra("Context");
        String date=intent.getStringExtra("Date");
        Toast.makeText(this,label+context+date,Toast.LENGTH_LONG).show();
        TextView labelView=findViewById(R.id.label4);
        TextView contextView=findViewById(R.id.context4);
        labelView.setText(label);
        contextView.setText(context);
        imageSlider=findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.sonpicture, null));
        imageList.add(new SlideModel(R.drawable.fatherpicture,"Loaded", null));
        imageSlider.setImageList(imageList,null);
    }
}