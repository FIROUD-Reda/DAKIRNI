package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class displayImage extends AppCompatActivity {
    ImageView imageView ;
    TextView titre ,msgtext ;
    String stringimage,title,msg ;
    Timer timer2;
    TimerTask myTimerTask12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        Bundle extras = getIntent().getExtras();
        if (timer2 != null) {
            timer2.cancel();
        }

        timer2 = new Timer();
        if (extras != null) {
            stringimage=extras.getString("image");
            title=extras.getString("title");
            msg=extras.getString("msg");
            ImageView carView =findViewById(R.id.imageView);
            titre=findViewById(R.id.titleR);
            msgtext=findViewById(R.id.textR);
            byte[] decodedString = Base64.decode(stringimage, Base64.NO_WRAP);
            InputStream input=new ByteArrayInputStream(decodedString);
            Bitmap ext_pic = BitmapFactory.decodeStream(input);
            carView.setImageBitmap(ext_pic);
            titre.setText(title);
            msgtext.setText(msg);

            doTask();

        }
    }
    public void doTask() {
        myTimerTask12 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }



                });
            }
        };

        timer2.schedule(myTimerTask12, 2000);


    }

}