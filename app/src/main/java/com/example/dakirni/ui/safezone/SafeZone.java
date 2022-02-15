package com.example.dakirni.ui.safezone;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SafeZone {
    @SerializedName("safezone_type")
    String safeZoneType;
    @SerializedName("safezone_lat")
    ArrayList<Double> safeZoneLat;
    @SerializedName("safezone_lng")
    ArrayList<Double> safeZoneLang;
    @SerializedName("fatherKey")
    String fatherKey;

    public String getFatherKey() {
        return fatherKey;
    }

    public void setFatherKey(String fatherKey) {
        this.fatherKey = fatherKey;
    }

    public String getSafeZoneType() {
        return safeZoneType;
    }

    public void setSafeZoneType(String safeZoneType) {
        this.safeZoneType = safeZoneType;
    }

    public ArrayList<Double> getSafeZoneLat() {
        return safeZoneLat;
    }

    public void setSafeZoneLat(ArrayList<Double> safeZoneLat) {
        this.safeZoneLat = safeZoneLat;
    }

    public ArrayList<Double> getSafeZoneLang() {
        return safeZoneLang;
    }

    public void setSafeZoneLang(ArrayList<Double> safeZoneLang) {
        this.safeZoneLang = safeZoneLang;
    }

    public SafeZone(String safeZoneType, ArrayList<Double> safeZoneLat, ArrayList<Double> safeZoneLang, String fatherKey) {
        this.safeZoneType = safeZoneType;
        this.safeZoneLat = safeZoneLat;
        this.safeZoneLang = safeZoneLang;
        this.fatherKey = fatherKey;
    }

    @Override
    public String toString() {
        return "SafeZone{" +
                "safeZoneType='" + safeZoneType + '\'' +
                ", safeZoneLat=" + safeZoneLat +
                ", safeZoneLang=" + safeZoneLang +
                '}';
    }
}
