package com.iecas.oceanologybigdata.model;

public class Ship {
    int id;
    String shipJson;
    String shipname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShipJson() {
        return shipJson;
    }

    public void setShipJson(String shipJson) {
        this.shipJson = shipJson;
    }

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }
}
