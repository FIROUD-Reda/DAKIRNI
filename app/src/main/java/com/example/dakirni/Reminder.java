package com.example.dakirni;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Reminder {

    private String reminder_id = UUID.randomUUID().toString();;
    private int hour, minute;
    private boolean is_active, is_repeating;
    private boolean mon, tue, wed, thu, fri, sat, sun;
    private String title;
    private String text;
    private String image;
    private String voice;

    public String getReminder_id() {
        return reminder_id;
    }

    public Reminder(int hour, int minute, boolean is_active, boolean is_repeating,
                    boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun,
                    String title, String text, String image, String voice) {
        this.hour = hour;
        this.minute = minute;
        this.is_active = is_active;
        this.is_repeating = is_repeating;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.title = title;
        this.text = text;
        this.image = image;
        this.voice = voice;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setis_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_repeating() {
        return is_repeating;
    }

    public void setIs_repeating(boolean is_repeating) {
        this.is_repeating = is_repeating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    // function to configure and set the reminder
    public void setReminder(Context context) {

        // set time using Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // if the time is already passed today, set reminder for tomorrow
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }

        // show a toast with different text depending on the reminder, if it is repeating or not
        if (!is_repeating) {
            String reminderText = null;
            try {
                reminderText = String.format("Reminder %s scheduled for %s at %02d:%02d", title,
                        calendar.get(calendar.DAY_OF_WEEK), hour, minute, reminder_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context, reminderText, Toast.LENGTH_LONG).show();

        } else {
            String toastText = String.format("Recurring Alarm %s scheduled for %s at %02d:%02d", title, getRepeatingDays(), hour, minute, reminder_id);
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
        }
        // set reminder to be active
        this.is_active = true;
    }

    // return a string with the days when the reminder is activated
    public String getRepeatingDays() {
        String repeating_days = "";
        if(this.mon) {
            repeating_days+= "mon, ";
        }
        if(this.tue) {
            repeating_days+= "tue, ";
        }
        if(this.wed) {
            repeating_days+= "wed, ";
        }
        if(this.thu) {
            repeating_days+= "thu, ";
        }
        if(this.fri) {
            repeating_days+= "fri, ";
        }
        if(this.sat) {
            repeating_days+= "sat, ";
        }
        if(this.sun) {
            repeating_days+= "sun, ";
        }
        return repeating_days;
    }
}