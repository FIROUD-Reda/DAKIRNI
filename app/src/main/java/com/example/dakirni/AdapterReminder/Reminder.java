package com.example.dakirni.AdapterReminder;


import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.UUID;

public class Reminder {

    @SerializedName("_id")
    private String reminder_id = UUID.randomUUID().toString();
    @SerializedName("minute")
    private int  minute;
    @SerializedName("hour")
    private int hour;
    @SerializedName("isRepeat")
    private boolean is_repeating;
    @SerializedName("is_active")
    private boolean is_active;
    //Reminder_days
    private boolean mon, tue, wed, thu, fri, sat, sun;
    @SerializedName("Reminder_title")
    private String title;
    @SerializedName("Reminder_content")
    private String text;
    @SerializedName("photo")
    private String image;
    @SerializedName("Reminder_voice")
    private String voice;
    private String key;

    /**/    public String getReminder_id() {
        return reminder_id;
    }

    public Reminder(int hour, int minute, boolean is_active, boolean is_repeating,
                    boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun,
                    String title, String text, String image, String voice, String key) {
        this.hour = hour;
        this.minute = minute;
        this.is_active = true;
        this.is_repeating = is_repeating;
        this.mon = true;
        this.tue = true;
        this.wed = true;
        this.thu = true;
        this.fri = true;
        this.sat = true;
        this.sun = true;
        this.title = title;
        this.text = text;
        this.image = "image";
        this.voice = "voice";
        this.key=key ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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



    public void setReminder_id(String reminder_id) {
        this.reminder_id = reminder_id;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }



    public boolean isMon() {
        return mon;
    }

    public boolean isTue() {
        return tue;
    }

    public boolean isWed() {
        return wed;
    }

    public boolean isThu() {
        return thu;
    }

    public boolean isFri() {
        return fri;
    }

    public boolean isSat() {
        return sat;
    }

    public boolean isSun() {
        return sun;
    }

    @Override
    public String toString() {
        return "{" +
                "reminder_id='" + reminder_id + '\'' +
                ", minute=" + minute +
                ", hour=" + hour +
                ", is_repeating=" + is_repeating +
                ", is_active=" + is_active +
                ", mon=" + mon +
                ", tue=" + tue +
                ", wed=" + wed +
                ", thu=" + thu +
                ", fri=" + fri +
                ", sat=" + sat +
                ", sun=" + sun +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", voice='" + voice + '\'' +
                '}';
    }
}