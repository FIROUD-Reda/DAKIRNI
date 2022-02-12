package com.example.dakirni.backgroundServices;

import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dakirni.RetrofitInterface;
import com.example.dakirni.environements.environementVariablesOfDakirni;
import com.example.dakirni.msgsAdapter.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchMessages extends Service {
    private String BASE_URL = environementVariablesOfDakirni.backEndUrl;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    ArrayList<Message> msgsList = new ArrayList<>();
    MediaPlayer mediaPlayer;
    Handler mHandler = new Handler();
    String destinationFile;
    TextToSpeech textToSpeechOfThread;
    File file;
    ArrayList<Thread> threadArrayList = new ArrayList<>();

    Runnable mRunnableTask = new Runnable() {
        @Override
        public void run() {
            fetchMsgs();
            // this will repeat this task again at specified time interval
//            mHandler.postDelayed(this, 600000);
            mHandler.postDelayed(this, 50000);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        mHandler.postDelayed(mRunnableTask, 100);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private String getRecordingFilePath(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, fileName + ".mp3");
        return file.getPath();
    }

    private String getRecordingFilePathWithout(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, fileName);
        return file.getPath();
    }

    void decodeAudio(String stringedAudio, String FileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(getRecordingFilePath(FileName + "AUDIO"));
        fos.write(Base64.decode(stringedAudio.getBytes(), Base64.DEFAULT));
        fos.close();
    }


    private void fetchMsgs() {
        threadArrayList.clear();
        Call<List<Message>> call = retrofitInterface.getUndeliveredMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    msgsList.clear();
                    msgsList.addAll(response.body());
                    for (Message gottenMessage : msgsList) {
                        Thread threadOfMessages = new Thread(new Runnable() {
                            Message messageOfThread = gottenMessage;
                            String contentVoice;

                            @Override
                            public void run() {

                                destinationFile = "";

//                    ReadAMessage readMessages = new ReadAMessage(filesdir, getApplicationContext());
//                    readMessages.execute(gottenMessage);

                                Thread createMsg=new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
                                        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
                                        file = new File(musicDirectory, messageOfThread.getMsgLabel() + ".mp3");
                                    }
                                });
                                Thread threadOfSynthizingFile = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        destinationFile = "";
                                        System.out.println("hadi khass duz hya lwla");
                                        destinationFile = getRecordingFilePath(messageOfThread.getMsgLabel().replace(" ", "_"));
                                        System.out.println("ha 7na gadina l fichier db w ha path dyalo" + destinationFile);
                                        System.out.println("hadi khass duz hya tanya");
                                        textToSpeechOfThread = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                                            @Override
                                            public void onInit(int status) {
                                                int result = textToSpeechOfThread.setLanguage(Locale.US);
                                                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                                    Log.e("error", "This Language is not supported");
                                                }
                                                if (status == TextToSpeech.SUCCESS) {
                                                    if(!messageOfThread.getMsgVoice().equals("Message with no voice")) {
                                                        contentVoice = "The message labeled as : " + messageOfThread.getMsgLabel() + " says " + messageOfThread.getMsgContent() + "and the audio says";
                                                    }else{
                                                        contentVoice = "The message labeled as : " + messageOfThread.getMsgLabel() + " says " + messageOfThread.getMsgContent() ;
                                                    }
                                                    final String utteranceId = messageOfThread.getMsgLabel();
                                                    createMsg.start();
                                                    try {
                                                        createMsg.join();
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    textToSpeechOfThread.synthesizeToFile(contentVoice, null, file, utteranceId);
                                                    if (file.exists())
                                                        System.out.println("lol");
                                                    while (!file.exists()) {
                                                        System.out.println("mazal");
                                                    }
                                                    System.out.println("on peut lire le fichier" + file.canExecute());

                                                    System.out.println("ha 7na 3mrna l fichier db wha smito" + file.getPath());

                                                    System.out.println("hi reda sa7be 2 ");

                                                }
                                            }
                                        });

                                    }
                                });

                                Thread threadOfTextSpeech = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        threadOfSynthizingFile.start();
                                        try {
                                            threadOfSynthizingFile.join();
                                            Thread.sleep(4000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("hadi khass duz hya talta");
                                        if (!messageOfThread.getMsgVoice().equals("Message with no voice"))
                                            try {
                                                decodeAudio(messageOfThread.getMsgVoice(), messageOfThread.getMsgLabel());
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        MediaPlayer mp = new MediaPlayer();
                                        try {
                                            mp.setDataSource(getRecordingFilePath(messageOfThread.getMsgLabel()));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            mp.prepare();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        mp.start();
                                        while (mp.isPlaying()) {
                                        }
                                        System.out.println("ha 7na db an9raw lfichier w ha lmsg 3onwano" + messageOfThread.getMsgLabel());

                                    }
                                });
                                Thread threadLkhr = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        threadOfTextSpeech.start();
                                        try {
                                            threadOfTextSpeech.join();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(!messageOfThread.getMsgVoice().equals("Message with no voice")) {
                                        MediaPlayer mp = new MediaPlayer();
                                        try {
                                            mp.setDataSource(getRecordingFilePath(messageOfThread.getMsgLabel() + "AUDIO"));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            mp.prepare();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        mp.start();
                                        while (mp.isPlaying()) {
                                        }
                                    }
                                    }
                                });
                                Thread threadImage=new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        threadLkhr.start();
                                        try {
                                            threadLkhr.join();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(!messageOfThread.getMsgImage().equals("Message with no image")) {
                                            Intent intent = new Intent(FetchMessages.this, PhotoActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.putExtra("image", messageOfThread.getMsgImage());
                                            intent.putExtra("title", messageOfThread.getMsgLabel());
                                            intent.putExtra("msg", messageOfThread.getMsgContent());
                                            startActivity(intent);
                                        }
                                    }
                                });
                                threadImage.start();
//                    updateMsgStatus(gottenMessage);
                            }
                        });
                        threadArrayList.add(threadOfMessages);
                    }
                    Thread threadexec = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < threadArrayList.size(); i++) {
                                threadArrayList.get(i).start();
                                try {
                                    threadArrayList.get(i).join();
                                    Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    threadexec.start();
                }
            }


            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println("hi this is an error of getting undelivred messages" + t.getMessage());
            }
        });
    }

    private void updateMsgStatus(Message msgToBeUpdated) {
        Call<Message> call = retrofitInterface.updateMessage(msgToBeUpdated.getMsgId());
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                System.out.println("this is the updated message" + response);
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println("this is an error of updating the messages" + t.getMessage());
            }
        });
    }


}
