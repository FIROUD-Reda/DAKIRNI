package com.example.dakirni.msgsAdapter;

//import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {


  //  @SerializedName("msgId")
    private  Double msgId;
   // @SerializedName("msgLabel")
    private String msgLabel;
    //@SerializedName("msgContent")
    private String textContent;
   // @SerializedName("creationDate")
    private Date creationDate;
   // @SerializedName("msgVoice")
    private String[] voicesArray;
   // @SerializedName("msgImages")
    private String[] imagesArray;
   // @SerializedName("is_sent")
    private boolean is_sent;
   // @SerializedName("is_delivered")
    private boolean is_delivered;
   // @SerializedName("is_read")
    private boolean is_read;

    public Message() {
        this.msgId=Math.random()*10000000;
//        this.msgLabel = msgLabel;
//        this.textContent = textContent;
//        this.voicesArray = voicesArray;
//        this.imagesArray = imagesArray;
//        this.creationDate = new Date();
    }

    public Message(String msgLabel, String textContent, Date creationDate, String[] voicesArray, String[] imagesArray, boolean is_sent, boolean is_delivered, boolean is_read) {
        this.msgId = Math.random()*10000000;
        this.msgLabel = msgLabel;
        this.textContent = textContent;
        this.creationDate = creationDate;
        this.voicesArray = voicesArray;
        this.imagesArray = imagesArray;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
        this.is_read = is_read;
    }

    public Double getMsgId() {
        return msgId;
    }

    public void setMsgId(Double msgId) {
        this.msgId = msgId;
    }
    public boolean isIs_sent() {
        return is_sent;
    }

    public void setIs_sent(boolean is_sent) {
        this.is_sent = is_sent;
    }

    public boolean isIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(boolean is_delivered) {
        this.is_delivered = is_delivered;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public String getMsgLabel() {
        return msgLabel;
    }

    public void setMsgLabel(String msgLabel) {
        this.msgLabel = msgLabel;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String[] getVoicesArray() {
        return voicesArray;
    }

    public void setVoicesArray(String[] voicesArray) {
        this.voicesArray = voicesArray;
    }

    public String[] getImagesArray() {
        return imagesArray;
    }

    public void setImagesArray(String[] imagesArray) {
        this.imagesArray = imagesArray;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
