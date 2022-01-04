package com.example.dakirni.msgsAdapter;

import java.util.Date;

public class Message {
    private String msgLabel;
    private String textContent;


    private Date creationDate;
    private String[] voicesArray;
    private String[] imagesArray;

    public Message(String msgLabel, String textContent, String[] voicesArray, String[] imagesArray) {
        this.msgLabel = msgLabel;
        this.textContent = textContent;
        this.voicesArray = voicesArray;
        this.imagesArray = imagesArray;
        this.creationDate = new Date();
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
