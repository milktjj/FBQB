package com.iecas.oceanologybigdata.model;

public class OBStationData {
    String imgurl;
    double lon;
    double lat;
    String name;
    int id;
    String waterL;
    String currentL;
    String elementL;
    String creatureL;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaterL() {
        return waterL;
    }

    public void setWaterL(String waterL) {
        this.waterL = waterL;
    }

    public String getCurrentL() {
        return currentL;
    }

    public void setCurrentL(String currentL) {
        this.currentL = currentL;
    }

    public String getElementL() {
        return elementL;
    }

    public void setElementL(String elementL) {
        this.elementL = elementL;
    }

    public String getCreatureL() {
        return creatureL;
    }

    public void setCreatureL(String creatureL) {
        this.creatureL = creatureL;
    }
}
