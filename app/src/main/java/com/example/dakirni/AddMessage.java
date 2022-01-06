package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dakirni.msgsAdapter.Message;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import retrofit2.Retrofit;

public class AddMessage extends AppCompatActivity {
    //Variables message voice
    ImageView play, record, stop, delete;
    //private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "";
    //    ImageButton play, record, stop;
    private static int MICROPHONE_PERMISSION_CODE = 201;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    //Variables upload message
    TextView upload;
    TextView send;
    ImageView uploaded_image;
    Bitmap selectedImage;
    InputStream imageStream;
    Uri imageUri;
    public final Integer RESULT_LOAD_IMG = 1;
    EditText messageTitle, messageContent;
    //testing
    ImageView experimentalAudio;
    String stringedAudio;
    String stringedImage;
    Message msgToBeSent = new Message();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        retrofitInterface=retrofit.create(RetrofitInterface.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        //Record
        record = findViewById(R.id.voice);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        send = findViewById(R.id.send);
        delete = findViewById(R.id.deleteRecord);
        messageTitle = findViewById(R.id.message_title_input);
        messageContent = findViewById(R.id.message_content_input);
        //this was just for testing
        experimentalAudio = findViewById(R.id.playdecoded);
        experimentalAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"encoding",Toast.LENGTH_LONG).show();
//                stringedImage=encodeImage(selectedImage);
//                messageContent.setText(stringedImage);
//                Toast.makeText(getApplicationContext(),"encoded",Toast.LENGTH_LONG).show();

                Bitmap bitmap= decodeImage(messageContent.getText().toString());

                uploaded_image.setImageBitmap(bitmap);
//            }

            //            @Override
//            public void onClick(View view) {
//
//                try {
//                    decodeAudio();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }


        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendingMessage();
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMichrofonePresent()) {
                    getMichrophonePermission();
                }
                try {
//                    play.setImageResource(R.drawable.fatherimg);
                    stringedAudio ="";
                    messageContent.setText(stringedAudio);
                    record.setImageResource(R.drawable.its_recording);
                    stop.setVisibility(View.VISIBLE);
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setOutputFile(getRecordingFilePath("contentmsgrecorder" + msgToBeSent.getMsgId()));
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    Toast.makeText(getApplicationContext(), "Recording Started .....", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    stop.setVisibility(View.INVISIBLE);
                    record.setImageResource(R.drawable.start_recording);
                }
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaPlayer = new MediaPlayer();
                    String recordingPathFile = getRecordingFilePath("contentmsgrecorder" + msgToBeSent.getMsgId());
                    mediaPlayer.setDataSource(recordingPathFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Recording playing .....", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                Toast.makeText(getApplicationContext(), "Recording Stoped .....", Toast.LENGTH_SHORT).show();
                record.setImageResource(R.drawable.start_recording);
                play.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                record.setVisibility(View.INVISIBLE);
                encodeAudio();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringedAudio="";
                messageContent.setText(stringedAudio);
                delete.setVisibility(View.INVISIBLE);
                play.setVisibility(View.INVISIBLE);
                record.setVisibility(View.VISIBLE);

            }
        });


        // Upload Image
        upload = findViewById(R.id.upload_image);
        uploaded_image = findViewById(R.id.uploaded_image);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });


    }



    private void handleSendingMessage() {
        String title = messageTitle.getText().toString();
        String content = messageContent.getText().toString();
        String voices=stringedAudio;
//        String images=;
//        Message message=new Message(title,content,);


    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);


//                uploaded_image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        // compress Bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG,40,stream);
        // Initialize byte array
        byte[] bytes=stream.toByteArray();
        // get base64 encoded string
        String a=Base64.encodeToString(bytes,Base64.DEFAULT);
        return a;
        // set encoded text on textview
    }
    private Bitmap decodeImage(String stringedImage) {
        byte[] bytes=Base64.decode(stringedImage,Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView
       return bitmap;
    }

    //Record

    private boolean isMichrofonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private void getMichrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }


    private String getRecordingFilePath(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        File file = new File(musicDirectory, "testRecordingFile" + ".mp3");
        File file = new File(musicDirectory, fileName + ".mp3");
        return file.getPath();
    }

    void encodeAudio() {
//        if (ContextCompat.checkSelfPermission(AddMessage.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            // when permission is nor granted
//            // request permission
//            ActivityCompat.requestPermissions(AddMessage.this
//                    , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
//
//        } else {
//            File audioFile = new File(Environment.getExternalStorageDirectory() + "/encodedFile.txt");
//            long fileSize = audioFile.length();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] audioBytes;
//             = null;
        try {
            FileInputStream fis = new FileInputStream(getRecordingFilePath("contentmsgrecorder" + msgToBeSent.getMsgId()));
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
            audioBytes = baos.toByteArray();

            // Here goes the Base64 string
            stringedAudio = Base64.encodeToString(audioBytes, Base64.DEFAULT);
             messageContent.setText(stringedAudio);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void decodeAudio() throws IOException {
        FileOutputStream fos = new FileOutputStream(getRecordingFilePath("decodeFile"));
        fos.write(Base64.decode(stringedAudio.getBytes(), Base64.DEFAULT));
        fos.close();

        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(getRecordingFilePath("decodeFile"));
        mp.prepare();
        mp.start();

    }
//    }

}