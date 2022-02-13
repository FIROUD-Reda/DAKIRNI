package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.dakirni.backgroundServices.FetchMessages;

public class FatherChoiceActivity extends AppCompatActivity {
Button list_sons, game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father_choice);
        list_sons = findViewById(R.id.list_sons_btn);
        game = findViewById(R.id.game_btn);
        list_sons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), List_son.class);
                startActivity(intent);
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });
        Intent serviceIntent = new Intent(this, FetchMessages.class);
        serviceIntent.putExtra("filesdir",this.getExternalFilesDir(Environment.DIRECTORY_MUSIC));
        startService(serviceIntent);
    }
}