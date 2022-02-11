package com.example.dakirni.msgsAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Message {


    @SerializedName("msgId")
    private  Double msgId;
    @SerializedName("msgLabel")
    private String msgLabel;
    @SerializedName("msgColor")
    private String msgColor;
    @SerializedName("msgContent")
    private String msgContent;
    @SerializedName("msgCreationDate")
    private Date msgCreationDate;
    @SerializedName("msgVoice")
    private String msgVoice;
    @SerializedName("msgImage")
    private String msgImage;
    @SerializedName("is_sent")
    private boolean is_sent;
    @SerializedName("is_delivered")
    private boolean is_delivered;
    @SerializedName("is_read")
    private boolean is_read;

    public Message() {
        this.msgId=Math.random()*10000000;
//        this.msgLabel = msgLabel;
//        this.textContent = textContent;
//        this.voicesArray = voicesArray;
//        this.imagesArray = imagesArray;
//        this.creationDate = new Date();
    }

    public Message(String msgLabel,String msgColor, String msgContent, Date msgCreationDate, String msgVoice, String msgImage, boolean is_sent, boolean is_delivered, boolean is_read) {
        this.msgId = Math.random()*10000000;
        this.msgLabel = msgLabel;
        this.msgColor=msgColor;
        this.msgContent = msgContent;
        this.msgCreationDate = msgCreationDate;
        this.msgVoice = msgVoice;
        this.msgImage = msgImage;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
        this.is_read = is_read;
    }

    public String getMsgColor() {
        return msgColor;
    }

    public void setMsgColor(String msgColor) {
        this.msgColor = msgColor;
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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgVoice() {
        return msgVoice;
    }

    public void setMsgVoice(String msgVoice) {
        this.msgVoice = msgVoice;
    }

    public String getMsgImage() {
        return msgImage;
    }

    public void setMsgImage(String msgImage) {
        this.msgImage = msgImage;
    }

    public Date getMsgCreationDate() {
        return msgCreationDate;
    }

    public void setMsgCreationDate(Date msgCreationDate) {
        this.msgCreationDate = msgCreationDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId=" + msgId +
                ", msgLabel='" + msgLabel + '\'' +
                ", msgColor='" + msgColor + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgCreationDate=" + msgCreationDate +
                ", msgVoice='" + msgVoice + '\'' +
                ", msgImage='" + msgImage + '\'' +
                ", is_sent=" + is_sent +
                ", is_delivered=" + is_delivered +
                ", is_read=" + is_read +
                '}';
    }
}
