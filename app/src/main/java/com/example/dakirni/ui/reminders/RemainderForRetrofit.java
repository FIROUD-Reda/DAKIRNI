package com.example.dakirni.ui.reminders;

import java.util.List;

public class RemainderForRetrofit {

    private String Reminder_content ;
    private String Reminder_title ;
    Boolean isRepeat;
    Boolean is_sent =true;
    private String Reminder_voice ;
    private boolean mon, tue, wed, thu, fri, sat, sun;
    private String key ;

    int hour,minute ;
    private String photo;
//
//    public RemainderForRetrofit(String reminder_content, String reminder_title) {
//        Reminder_content = reminder_content;
//        Reminder_title = reminder_title;
//    }

    public RemainderForRetrofit() {
    }


    public RemainderForRetrofit(String reminder_content, String reminder_title, Boolean isRepeat, Boolean is_sent, String reminder_voice, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun, int hour, int minute, String photo, String key) {
        Reminder_content = reminder_content;
        Reminder_title = reminder_title;
        this.isRepeat = isRepeat;
        this.is_sent = is_sent;
        Reminder_voice = reminder_voice;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.hour = hour;
        this.minute = minute;
        this.photo = photo;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setReminder_voice(String reminder_voice) {
        Reminder_voice = reminder_voice;
    }

    public String getPhoto() {
        return photo;
    }

    public String getReminder_voice() {
        return Reminder_voice;
    }


    public void setIs_sent(Boolean is_sent) {
        this.is_sent = is_sent;
    }

    public Boolean getIs_sent() {
        return is_sent;
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

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }







    public void setRepeat(Boolean repeat) {
        isRepeat = repeat;
    }

    public Boolean getRepeat() {
        return isRepeat;
    }

    public String getReminder_content() {
        return Reminder_content;
    }

    public String getReminder_title() {
        return Reminder_title;
    }

    public void setReminder_content(String reminder_content) {
        Reminder_content = reminder_content;
    }

    public void setReminder_title(String reminder_title) {
        Reminder_title = reminder_title;
    }
}
