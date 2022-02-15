package com.example.dakirni.ui.reminders;

import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dakirni.AdapterReminder.Reminder;
import com.example.dakirni.R;
import com.example.dakirni.database.son.SonDbHelper;
import com.example.dakirni.env;
import com.example.dakirni.environements.environementVariablesOfDakirni;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SetReminderActivity extends AppCompatActivity {
    String Image, voicemsg, Text, Title,reminder_id;
    Boolean Tue1, Sat1, Thu1, mon1, Sun1, Wed1, Fri1;
    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    File audiofile = null;
    static final String TAG = "MediaRecording";
    ImageView play, record, stop, delete;
    private static int MICROPHONE_PERMISSION_CODE = 201;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    private int hour, minute;
    private String title, text;
    private String image;
    private String voice;
    private boolean is_active, is_repeating;
    private boolean mon, tue, wed, thu, fri, sat, sun;
    private Boolean isRepeat, is_sent;
    private Boolean[] Reminder_days = {false, false, false, false, false, false, false,};//= ;
    TextView upload;
    TextView send;
    ImageView uploaded_image;
    Bitmap selectedImage;
    InputStream imageStream;
    Uri imageUri;
    public final Integer RESULT_LOAD_IMG = 1;
    ImageView experimentalAudio;
    String stringedAudio;
    String stringedImage;
    RemainderForRetrofit remainderForRetrofit;
    ApiInterfaceRemainder apiInterfaceRemainder;
    Retrofit retrofit;
    EditText input_title, input_text, invisiblef,textid;
    TextView Uplaod_reminder;
    private ImageView startTV, stopTV, playTV, stopplayTV, statusTV;

    private MediaRecorder mRecorder;

    private MediaPlayer mPlayer;
    Button  updateR,deleteR  ;
    Boolean updateaudio =false ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        Toast.makeText(getApplicationContext(), env.key, Toast.LENGTH_SHORT).show();
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/"+"123"+".3gp";
        stringedAudio="";

        retrofit = new Retrofit.Builder()
                .baseUrl(environementVariablesOfDakirni.backEndUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterfaceRemainder = retrofit.create(ApiInterfaceRemainder.class);
        invisiblef = findViewById(R.id.aloRe);
        startTV = findViewById(R.id.btnRecord);
        stopTV = findViewById(R.id.btnStop);
        playTV = findViewById(R.id.btnPlay);
        stopplayTV = findViewById(R.id.btnStopPlay);
        TimePicker timePicker_reminder_time = findViewById(R.id.timePicker_reminder_time);
        Button btn_cancel = findViewById(R.id.btn_cancel_reminder);
        Button btn_confirm = findViewById(R.id.btn_confirm_reminder);
        input_title = findViewById(R.id.input_reminder_title);
        input_text = findViewById(R.id.input_reminder_text);
        updateR=findViewById(R.id.update) ;
        TextView tv_title_interface = findViewById(R.id.tv_set_reminder);
        Uplaod_reminder = findViewById(R.id.tv_reminder_image);
        CheckBox check_mon = findViewById(R.id.checkBox_Mon);
        CheckBox check_tue = findViewById(R.id.checkBox_Tue);
        CheckBox check_wed = findViewById(R.id.checkBox_Wed);
        CheckBox check_thu = findViewById(R.id.checkBox_Thu);
        CheckBox check_fri = findViewById(R.id.checkBox_Fri);
        CheckBox check_sat = findViewById(R.id.checkBox_Sat);
        CheckBox check_sun = findViewById(R.id.checkBox_Sun);
        CheckBox check_repeat = findViewById(R.id.checkBox_repeat);
        deleteR=findViewById(R.id.deleteR);
        deleteR.setBackgroundColor(getResources().getColor(R.color.red));
        deleteR.setVisibility(View.INVISIBLE);
        boolean is_new;
        int hourdays, minutedays;

        check_repeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRepeat = isChecked;
            }
        });
        check_mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mon = isChecked;
            }
        });
        check_tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tue = isChecked;
            }
        });
        check_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wed = isChecked;
            }
        });
        check_thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                thu = isChecked;
            }
        });
        check_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fri = isChecked;
            }
        });
        check_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sat = isChecked;
            }
        });
        check_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sun = isChecked;
            }
        });
        timePicker_reminder_time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute1) {
                hour = hourOfDay;
                minute = minute1;
            }
        });


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            deleteR.setVisibility(View.VISIBLE);
            btn_confirm.setVisibility(View.INVISIBLE);
            updateR.setVisibility(View.VISIBLE);
            reminder_id = extras.getString("REMINDER_ID");
            hourdays = extras.getInt("Hour");
            minutedays = extras.getInt("Minute");
            Title = extras.getString("Title");
            Text = extras.getString("Text");
            voicemsg = extras.getString("voice");
            isRepeat = extras.getBoolean("repeat");
            check_repeat.setChecked(isRepeat);
            check_mon.setChecked(extras.getBoolean("Mon"));
            check_sun.setChecked(extras.getBoolean("Sun"));
            check_wed.setChecked(extras.getBoolean("Wed"));
            check_fri.setChecked(extras.getBoolean("Fri"));
            check_thu.setChecked(extras.getBoolean("Thu"));
            check_tue.setChecked(extras.getBoolean("Tue"));
            check_sat.setChecked(extras.getBoolean("Sat"));

            Image = extras.getString("Image");
            reminder_id = extras.getString("REMINDER_ID");
            reminder_id = extras.getString("REMINDER_ID");
         //
                 updateaudio =true ;
            stringedAudio = voicemsg;
            if(stringedAudio != null){
                try {
                    Log.d("nabil11","11");
                    decodeAudiokh(stringedAudio);
                    Log.d("nabil33","rah decoda ");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            stringedImage = Image;
            input_text.setText(Text);
            input_title.setText(Title);
            timePicker_reminder_time.setHour(hourdays);
            timePicker_reminder_time.setMinute(minutedays);



//            decodeAudio1(stringedAudio,null,getRecordingFilePath(mFileName),mPlayer);

            Toast.makeText(getApplicationContext(),  reminder_id, Toast.LENGTH_SHORT).show();
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetReminderActivity.this, RemindersFragment.class);
                startActivity(intent);
            }
        });
        startTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startRecording();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        stopTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseRecording();

            }
        });
        playTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playAudio("decodedFile");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        stopplayTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePlaying();
            }
        });
        upload = findViewById(R.id.upload_image_reminder);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringedImage = "";
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
        updateR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = input_title.getText().toString();
                String content = input_text.getText().toString();
                String voices = stringedAudio;
                String images = stringedImage;
                String number123 =reminder_id;
                RemainderForRetrofit remainderForRetrofit2 = new RemainderForRetrofit(input_text.getText().toString(), input_title.getText().toString(), isRepeat, is_sent, voices, mon, tue, wed, thu, fri, sat, sun, hour, minute, images,environementVariablesOfDakirni.key);
                Call<Void> call = apiInterfaceRemainder.updateReminder(reminder_id ,remainderForRetrofit2);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), response.body()+"", Toast.LENGTH_SHORT).show();
                        Log.d("testupdate",response.body()+"");
                        finish();
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        deleteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = apiInterfaceRemainder.removereminder(reminder_id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Toast.makeText(getApplicationContext(), "remaider removed", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String title = input_title.getText().toString();
                String content = input_text.getText().toString();
                String voices = stringedAudio;
                String images = stringedImage;
                SonDbHelper sonDbHelper = new SonDbHelper(getApplicationContext());
                ArrayList<String> arrayList = sonDbHelper.lireToken();
                StringBuffer maListe = new StringBuffer();

                try {
                    Iterator<String> iter = arrayList.iterator();
                    while (iter.hasNext()) {
                        maListe.append(iter.next());
                    }
                    Toast.makeText(getApplicationContext(),maListe.toString(),Toast.LENGTH_SHORT).show();
                }catch (ArrayIndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext(),"Aucun Résultat trouvé !",Toast.LENGTH_SHORT).show();
                }
                RemainderForRetrofit remainderForRetrofit1 = new RemainderForRetrofit(input_text.getText().toString(), input_title.getText().toString(), isRepeat, is_sent, voices, mon, tue, wed, thu, fri, sat, sun, hour, minute, images,environementVariablesOfDakirni.key);
                Call<Void> call = apiInterfaceRemainder.addReminder(maListe.toString(),remainderForRetrofit1);
                   call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        input_text.setText("");
                        input_title.setText("");
                        stringedAudio = "";
                        stringedImage = "";
                          finish();
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {

                imageUri = data.getData();
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            selectedImage = BitmapFactory.decodeStream(imageStream);
                stringedImage = encodeImage(selectedImage);
                Uplaod_reminder.setText("Image successfully uploaded");
               } else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
    private String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream);
        byte[] bytes = stream.toByteArray();
        String a = Base64.encodeToString(bytes, Base64.DEFAULT);
        return a;
    }

    private Bitmap decodeImage(String stringedImage) {
        byte[] bytes = Base64.decode(stringedImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

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
        File file = new File(musicDirectory, fileName + ".mp3");
        return file.getPath();
    }

    void encodeAudio() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] audioBytes;
        try {
            FileInputStream fis = new FileInputStream(getRecordingFilePath(mFileName));
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
            audioBytes = baos.toByteArray();
            stringedAudio = Base64.encodeToString(audioBytes, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            stringedAudio = "";
            e.printStackTrace();
        } catch (IOException e) {
            stringedAudio = "";
            e.printStackTrace();
        }

    }

    void decodeAudio() throws IOException {
        FileOutputStream fos = new FileOutputStream(getRecordingFilePath(mFileName));
        fos.write(Base64.decode(stringedAudio.getBytes(), Base64.DEFAULT));
        fos.close();
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(getRecordingFilePath(mFileName));
        mp.prepare();
        mp.start();

    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void startRecording() throws IOException {
         stringedAudio = "";
        if (CheckPermissions()) {

            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/"+"123"+".3gp";
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mFileName);
            if(mFileName!=null)
                System.out.println("had file rah machi null"+mFileName);
            try {
                mRecorder.prepare();
                mRecorder.start();
            } catch (IOException e) {
                System.out.println("makhdamch prepare");
            }

        } else {
            RequestPermissions();
        }
    }

    public void pauseRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] audioBytes;
        try {
            FileInputStream fis = new FileInputStream(mFileName);
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);
            audioBytes = baos.toByteArray();
            stringedAudio = Base64.encodeToString(audioBytes, Base64.DEFAULT);
         //   Toast.makeText(getApplicationContext(), stringedAudio, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            stringedAudio = "";
            e.printStackTrace();
        } catch (IOException e) {
            stringedAudio = "";
            e.printStackTrace();
        }
    }

    public void playAudio(String destFileName) throws IOException {
        Log.d("nabil23","rani fi lbadya day function playaudio");


        if(updateaudio) {
            Log.d("nabil23","rani wast condition dyal updateaudio");

            try {
                mediaPlayer = new MediaPlayer();
                String recordingPathFile = getRecordingFilePath(destFileName);
                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(recordingPathFile));
                mediaPlayer.prepare();
                mediaPlayer.start();
                while(mediaPlayer.isPlaying()){}
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
            Log.d("nabil24","rani fi end dayl function playaudio");

        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
    }

    public void pausePlaying() {
        mPlayer.release();
        mPlayer = null;

    }
    public void playAudiokh(String destFileName) {
        try {
            Log.d("nabil23","rani fi lbadya day function playaudio");

            mediaPlayer = new MediaPlayer();
            String recordingPathFile = getRecordingFilePath(destFileName);
//            mediaPlayer.setDataSource(recordingPathFile);
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(recordingPathFile));
            mediaPlayer.prepare();
            mediaPlayer.start();
            while(mediaPlayer.isPlaying()){}
            mediaPlayer.release();
            Log.d("nabil24","rani fi end dayl function playaudio");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void decodeAudiokh(String stringedAudio) throws IOException {
        Log.d("nabil21","rani fi lbadya day function decode");
        FileOutputStream fos = new FileOutputStream(getRecordingFilePath("decodedFile"));
        fos.write(Base64.decode(stringedAudio.getBytes(), Base64.DEFAULT));
        fos.close();
        Log.d("nabil22","rani salit function decode");
    }
    private void decodeAudio1(String base64AudioData, File fileName, String path, MediaPlayer mp) {

        try {

            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(Base64.decode(base64AudioData.getBytes(), Base64.DEFAULT));
            fos.close();

            mp = new MediaPlayer();
            mp.setDataSource(path);
            mp.prepare();
            mp.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}