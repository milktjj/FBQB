package com.iecas.oceanologybigdata.model;

import java.sql.Timestamp;

public class QBCtd {
    Timestamp timestamp;
    double temperature;
    double sality;
    double pressure;
    String time;
    String date;
    String dateTime;

    public String getDate() {
        return date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getSality() {
        return sality;
    }

    public void setSality(double sality) {
        this.sality = sality;
    }
}
