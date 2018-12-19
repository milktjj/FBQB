package com.iecas.oceanologybigdata.model;

public class SailTrack {
    int id;
    String sailname;
    double lon;
    double lat;
    String datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSailname() {
        return sailname;
    }

    public void setSailname(String sailname) {
        this.sailname = sailname;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
