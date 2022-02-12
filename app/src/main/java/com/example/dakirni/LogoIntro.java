package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.dakirni.database.father.FatherDbHelper;
import com.example.dakirni.database.son.SonDbHelper;

public class LogoIntro extends AppCompatActivity {
    ImageView imageView;
    Animation topanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_logo_intro);
        imageView = findViewById(R.id.logo);

        topanim = AnimationUtils.loadAnimation(this,R.anim.animation_top);
        imageView.setAnimation(topanim);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        SonDbHelper sonDbHelper = new SonDbHelper(getApplicationContext());
        FatherDbHelper fatherDbHelper = new FatherDbHelper(getApplicationContext());


        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);            }
        }, 3000);


    }
}