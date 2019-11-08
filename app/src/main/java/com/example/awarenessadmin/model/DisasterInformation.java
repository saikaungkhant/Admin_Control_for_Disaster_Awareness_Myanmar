package com.example.awarenessadmin.model;

public class DisasterInformation {
    public int id;
    public String disaster;
    public String info;
    public double latitude;
    public double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public DisasterInformation() {
    }

    public DisasterInformation(int id, String disaster, String info, double latitude, double longitude) {
        this.id = id;
        this.disaster = disaster;
        this.info = info;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}