package com.iecas.oceanologybigdata.model;

import java.sql.Timestamp;

public class QBAqd {
    Timestamp timestamp;
    double pressure;
    double cuSpeed;
    double cvSpeed;
    double cwSpeed;
    String time;
    String date;
    String dateTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getCuSpeed() {
        return cuSpeed;
    }

    public void setCuSpeed(double cuSpeed) {
        this.cuSpeed = cuSpeed;
    }

    public double getCvSpeed() {
        return cvSpeed;
    }

    public void setCvSpeed(double cvSpeed) {
        this.cvSpeed = cvSpeed;
    }

    public double getCwSpeed() {
        return cwSpeed;
    }

    public void setCwSpeed(double cwSpeed) {
        this.cwSpeed = cwSpeed;
    }


}
