package com.example.dakirni;

import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dakirni.AdapterFather.ModelClass;
import com.example.dakirni.AdapterFather.MyAdapter;
import com.example.dakirni.AdapterReminder.Reminder;
import com.example.dakirni.ui.reminders.ApiInterfaceRemainder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class List_son extends AppCompatActivity {

    RecyclerView sonrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> sonList;
    MyAdapter adapter;
    ApiInterfaceRemainder apiInterfaceRemainder;
    Retrofit retrofit;
    private List<Reminder> reminders;
    private MediaPlayer mPlayer;
    private static String mFileName = null;
    MediaPlayer mediaPlayer;
    String  imagebase ="";
    String  stringedAudio ="";
    int res ;
    Timer timer;
    TimerTask myTimerTask;
    String strDate;
    int m=58;
    int h=1;
    Calendar calendar ;
    ArrayList<String>  audiotable=new ArrayList<>();
    ArrayList<Integer>  peroide1=new ArrayList<>();
    ArrayList<Integer>  hour=new ArrayList<>();
    ArrayList<Integer>  minute=new ArrayList<>();
    ArrayList<String>  imagetable=new ArrayList<>();
    ArrayList<String>  msgTextTable=new ArrayList<>();
    ArrayList<String>  titleMsgTable=new ArrayList<>();
   private int[] peroide = {1000,10000,20000};//= ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_father);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.101:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterfaceRemainder = retrofit.create(ApiInterfaceRemainder.class);
        //get time now
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        final String strDate = simpleDateFormat1.format(calendar.getTime());
        String[] tab =strDate.split(":");
        int hourNow =Integer.parseInt(tab[0]);
        int minNow  =Integer.parseInt(tab[1]);
        Call<List<Reminder>> call = apiInterfaceRemainder.getRemaiders("2");
        call.enqueue(new Callback<List<Reminder>>() {
            @Override
            public void onResponse(Call<List<Reminder>> call, Response<List<Reminder>> response) {
                Calendar now = Calendar.getInstance();
                int day = now.get(Calendar.DAY_OF_WEEK);
                for (Reminder a : response.body()) {
                    if (day == Calendar.MONDAY) {
                        if (a.isMon() == true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "tsana htal simana jaya", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                    else  if (day == Calendar.TUESDAY) {
                        if (a.isTue() == true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "tsana htal simana jaya", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                    else if (day == Calendar.WEDNESDAY) {
                        if (a.isWed() == true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "tsana htal simana jaya", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                    else if (day == Calendar.THURSDAY) {
                        if (a.isThu() == true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "tsana htal simana jaya", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                    else if (day == Calendar.FRIDAY) {
                        if (a.isFri() == true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "tsana htal simana jaya", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                    else if (day == Calendar.SATURDAY) {
                        if (a.isSat()== true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "tsana htal simana jaya", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                    else if (day == Calendar.SUNDAY) {
                        if (a.isSun()== true)
                        {
                            res =(a.getHour()-hourNow)*60 ; //convert to minute
                            res=res+(a.getMinute()-minNow);//get all minute
                            res=res*60000;
                            if (res < 0) {
                                Toast.makeText(getApplicationContext(), "" +
                                        "", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "t9dar doz lyouma", Toast.LENGTH_SHORT).show();
                                audiotable.add(a.getVoice());
                                hour.add(a.getHour());
                                minute.add(a.getMinute());
                                imagetable.add(a.getImage());
                                titleMsgTable.add(a.getTitle());
                                msgTextTable.add(a.getText());
                            }
                        }
                    }
                }

                //calculer peroide

                for (int j=0 ;j<hour.size();j++){
                    res=0;
                    res =(hour.get(j)-hourNow)*60 ; //convert to minute
                    res=res+(minute.get(j)-minNow);//get all minute
                    res=res*60000;//get periode by mills
                    peroide1.add(res);
                }


                Log.d("body",response.body()+"");

                    Runnable r = new Runnable(){
                        public void run(){
                            for(int i=0;i<audiotable.size();i++){
                                sendmsg(audiotable.get(i),peroide1.get(i),i);
                            }

                        }};
                    new Thread(r).start();
            }

            @Override
            public void onFailure(Call<List<Reminder>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        initData();
        initRecyclerView();
    }
    private void initRecyclerView() {
        sonrecyclerView=findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        sonrecyclerView.setLayoutManager(layoutManager);
        adapter=new MyAdapter(sonList, this);
        sonrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
    private void initData() {
        sonList = new ArrayList<>();

        sonList.add(new ModelClass(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.bo,"nabil waldak","0665478912","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.girl,"saida abila","0854789221","_______________________________________"));
        sonList.add(new ModelClass(R.drawable.gi,"maroni zwina","123456789","_______________________________________"));





    }
    public void playAudio(String destFileName) throws IOException {
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

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
    }
    private String getRecordingFilePath(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, fileName + ".mp3");
        return file.getPath();
    }

   public void sendmsg(String a ,int b ,int i){
       timer=null ;
       mediaPlayer=null ;
       FileOutputStream fos = null;
       try {
           fos = new FileOutputStream(getRecordingFilePath("decodedFile"+i));
       } catch (FileNotFoundException fileNotFoundException) {
           fileNotFoundException.printStackTrace();
       }
       try {
           fos.write(Base64.decode(a.getBytes(), Base64.DEFAULT));
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }
       try {
           fos.close();
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }
       if (timer != null) {
           timer.cancel();
       }
       timer = new Timer();
       doTask(b,i);
   }
    public void doTask(int b,int i) {

        myTimerTask = new TimerTask() {
            public void run() {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
                final String strDate = simpleDateFormat.format(calendar.getTime());

                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            mediaPlayer = new MediaPlayer();
                            String recordingPathFile = getRecordingFilePath("decodedFile"+i);
                            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(recordingPathFile));
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            while(mediaPlayer.isPlaying()){}
                            mediaPlayer.release();
                            Intent image =new Intent(getApplicationContext() ,displayImage.class);
                            if(imagetable.get(i)!=null){
                                image.putExtra("image" ,imagetable.get(i));
                            }
                            if(msgTextTable.get(i)!=null){
                                image.putExtra("msg" ,msgTextTable.get(i));
                            }
                            if(titleMsgTable.get(i)!=null){
                                image.putExtra("title" ,titleMsgTable.get(i));
                            }

                            startActivity(image);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        Log.d("deley",b+"");
        timer.schedule(myTimerTask,b);

    }
}