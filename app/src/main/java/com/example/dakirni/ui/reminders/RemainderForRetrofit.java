package com.example.dakirni.ui.reminders;

import java.util.List;

public class RemainderForRetrofit {
    private String Reminder_content ;
    private String Reminder_title ;
    Boolean isRepeat;
    Boolean is_sent =true;

    List<Boolean> Reminder_days;
    int hour,minute ;

    public RemainderForRetrofit(String reminder_content, String reminder_title, Boolean isRepeat, Boolean is_sent, List<Boolean> reminder_days, int hour, int minute) {
        Reminder_content = reminder_content;
        Reminder_title = reminder_title;
        this.isRepeat = isRepeat;
        this.is_sent = true;
        Reminder_days = reminder_days;
        this.hour = hour;
        this.minute = minute;
    }

    public void setIs_sent(Boolean is_sent) {
        this.is_sent = is_sent;
    }

    public Boolean getIs_sent() {
        return is_sent;
    }

    public void setReminder_days(List<Boolean> reminder_days) {
        Reminder_days = reminder_days;
    }

    public List<Boolean> getReminder_days() {
        return Reminder_days;
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
