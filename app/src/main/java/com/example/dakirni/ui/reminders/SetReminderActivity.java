package com.example.dakirni.ui.reminders;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dakirni.AdapterReminder.Reminder;
import com.example.dakirni.R;

public class SetReminderActivity extends AppCompatActivity {

    private int hour, minute;
    private String title, text;
    private String image;
    private String voice;
    private boolean is_active, is_repeating;
    private boolean mon, tue, wed, thu, fri, sat, sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);

        TimePicker timePicker_reminder_time = findViewById(R.id.timePicker_reminder_time);
        Button btn_cancel = findViewById(R.id.btn_cancel_reminder);
        Button btn_confirm = findViewById(R.id.btn_confirm_reminder);
        Button btn_add_image = findViewById(R.id.btn_add_reminder_image);
        Button btn_add_voice = findViewById(R.id.btn_add_reminder_voice);
        EditText input_title = findViewById(R.id.input_reminder_title);
        EditText input_text = findViewById(R.id.input_reminder_text);
        TextView tv_image = findViewById(R.id.tv_reminder_image);
        TextView tv_voice = findViewById(R.id.tv_reminder_voice);
        CheckBox check_mon = findViewById(R.id.checkBox_Mon);
        CheckBox check_tue = findViewById(R.id.checkBox_Tue);
        CheckBox check_wed = findViewById(R.id.checkBox_Wed);
        CheckBox check_thu = findViewById(R.id.checkBox_Thu);
        CheckBox check_fri = findViewById(R.id.checkBox_Fri);
        CheckBox check_sat = findViewById(R.id.checkBox_Sat);
        CheckBox check_sun = findViewById(R.id.checkBox_Sun);
        CheckBox check_repeat = findViewById(R.id.checkBox_repeat);

        String reminder_id;
        boolean is_new;

        // To fill informations in case of update
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            is_new = extras.getBoolean("IS_NEW");
            if (!is_new) {
                // Get the information about reminder with the id from data base
                reminder_id = extras.getString("REMINDER_ID");
                // fill informations
            }
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetReminderActivity.this, RemindersFragment.class);
                startActivity(intent);
            }
        });

        // Click on confirm button, allow to add a new reminder or to update a specific reminder
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                // Get time (hour and minute) from the Time Picker
                hour = timePicker_reminder_time.getHour();
                minute = timePicker_reminder_time.getMinute();

                // set the title of reminder, if no title has been chosen, it is set to Reminder by default
                if (!input_title.getText().toString().equals("")) {
                    title = input_title.getText().toString();
                } else {
                    title = "Reminder";
                }

                // set the text, image and voice, for each one, if no one has been chosen, the default value is null
                if (!input_text.getText().toString().equals("")) {
                    text = tv_image.getText().toString();
                } else {
                    text = null;
                }
                if (!tv_image.getText().toString().equals("")) {
                    image = tv_image.getText().toString();
                } else {
                    image = null;
                }
                if (!tv_voice.getText().toString().equals("")) {
                    voice = tv_voice.getText().toString();
                } else {
                    voice = null;
                }

                // Repeat reminder every week set to true or false depending on the check_repeat CheckBox
                is_repeating = check_repeat.isChecked();

                // When confirming, the reminder is activated
                is_active = true;

                // The boolean variables corresponding to the days, are set to true or false depending to the CheckBoxes
                mon = check_mon.isChecked();
                tue = check_tue.isChecked();
                wed = check_wed.isChecked();
                thu = check_thu.isChecked();
                fri = check_fri.isChecked();
                sat = check_sat.isChecked();
                sun = check_sun.isChecked();

                // A new reminder created
                Reminder reminder = new Reminder(hour, minute, is_active, is_repeating, mon, tue, wed, thu, fri, sat, sun, title, text, image, voice);
            }
        });

    }
}