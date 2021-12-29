package com.example.dakirni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RemindersActivity extends AppCompatActivity {

    private Button btn_add_reminder;
    private RecyclerView recyclerView_reminders;
    private LinearLayoutManager linearLayoutManager;
    private ReminderAdapter reminderAdapter;
    private List<Reminder> reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        btn_add_reminder = findViewById(R.id.btn_add_reminder);

        btn_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemindersActivity.this, SetReminderActivity.class);
                startActivity(intent);
            }
        });

        initReminders();
        initRemindersRecyclerView();

    }

    public void initRemindersRecyclerView() {
        recyclerView_reminders = findViewById(R.id.recyclerView_reminders);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_reminders.setLayoutManager(linearLayoutManager);
        reminderAdapter = new ReminderAdapter(reminders, this);
        recyclerView_reminders.setAdapter(reminderAdapter);
        reminderAdapter.notifyDataSetChanged();
    }

    private void initReminders() {
        reminders = new ArrayList<>();

        reminders.add(new Reminder( 10, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder1","text1","image1","voice1"));
        reminders.add(new Reminder( 23, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder2","text1","image1","voice1"));
        reminders.add(new Reminder( 17, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder3","text1","image1","voice1"));
        reminders.add(new Reminder( 13, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder4","text1","image1","voice1"));
        reminders.add(new Reminder( 12, 10, true,false,
                true,false,true,false,true,false,true,
                "Reminder5","text1","image1","voice1"));



    }
}