package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MsgActivity extends AppCompatActivity {
    ImageView imageSlider;

    private Bitmap decodeImage(String stringedImage) {
        byte[] bytes = Base64.decode(stringedImage, Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        // set bitmap on imageView
        return bitmap;
    }

    private String getRecordingFilePath(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        File file = new File(musicDirectory, "testRecordingFile" + ".mp3");
        File file = new File(musicDirectory, fileName + ".mp3");
        return file.getPath();
    }

    void decodeAudio(String stringedAudio) throws IOException {
        FileOutputStream fos = new FileOutputStream(getRecordingFilePath("decodedFile"));
        fos.write(Base64.decode(stringedAudio.getBytes(), Base64.DEFAULT));
        fos.close();

//        MediaPlayer mp = new MediaPlayer();
//        mp.setDataSource(getRecordingFilePath("decodeFile"));
//        mp.prepare();
//        mp.start();

    }

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        Intent intent = getIntent();
        String label = intent.getStringExtra("Label");
        String context = intent.getStringExtra("Context");
        String image = intent.getStringExtra("Image");
        String voice = intent.getStringExtra("Voice");
        String date = intent.getStringExtra("Date");
//        Toast.makeText(this,label+context+date,Toast.LENGTH_LONG).show();
        TextView labelView = findViewById(R.id.label4);
        TextView contextView = findViewById(R.id.context4);
        ImageView playAudio = findViewById(R.id.playaudio);
        ImageView stopAudio = findViewById(R.id.stopaudio);
        labelView.setText("TITLE: " + label);
        contextView.setText(context);
        imageSlider = findViewById(R.id.imageView2);
        if (!image.equals("") && !image.equals("a")) {
            Bitmap bitmap = decodeImage(image);
            imageSlider.setImageBitmap(bitmap);
        }
        if (!voice.equals("") && !voice.equals("a")) {
            playAudio.setVisibility(View.VISIBLE);
            try {
                decodeAudio(voice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        playAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    stopAudio.setVisibility(View.VISIBLE);
                    mediaPlayer = new MediaPlayer();
                    String recordingPathFile = getRecordingFilePath("decodedFIle");
                    mediaPlayer.setDataSource(recordingPathFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Recording playing .....", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        stopAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
            }
        });


    }
}