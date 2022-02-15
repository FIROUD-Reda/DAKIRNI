package com.example.dakirni.AdapterContact;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("contact_id")
    private double contactId;
    @SerializedName("contact_image")
    private String imageview;
    @SerializedName("contact_name")
    private String textview1;
    @SerializedName("contact_number")
    private String textview2;
    @SerializedName("fatherKey")
    private String fatherKey;

    private String divider;

    public Contact() {
        this.contactId = Math.random() * 10000000;
    }

    public String getFatherKey() {
        return fatherKey;
    }

    public void setFatherKey(String fatherKey) {
        this.fatherKey = fatherKey;
    }

    public Contact(String imageview, String textview1, String textview2, String fatherKey) {
        this.contactId = Math.random() * 10000000;
        this.imageview = imageview;
        this.textview1 = textview1;
        this.textview2 = textview2;
        this.fatherKey = fatherKey;
    }

    public double getContactId() {
        return contactId;
    }

    public void setContactId(double contactId) {
        this.contactId = contactId;
    }

    public String getImageview() {
        return imageview;
    }

    public String getTextview1() {
        return textview1;
    }

    public String getDivider() {
        return divider;
    }


    public String getTextview2() {
        return textview2;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview;
    }

    public void setTextview1(String textview1) {
        this.textview1 = textview1;
    }

    public void setTextview2(String textview2) {
        this.textview2 = textview2;
    }
}
