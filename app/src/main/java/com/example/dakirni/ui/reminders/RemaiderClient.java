package com.example.dakirni.ui.reminders;

public class RemaiderClient {
    private  String _id ;
    private   String Reminder_title ;

    public RemaiderClient(String _id, String reminder_title) {
        this._id = _id;
        Reminder_title = reminder_title;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setReminder_title(String reminder_title) {
        Reminder_title = reminder_title;
    }

    public String get_id() {
        return _id;
    }

    public String getReminder_title() {
        return Reminder_title;
    }
}
