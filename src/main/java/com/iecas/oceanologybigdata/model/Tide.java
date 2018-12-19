package com.iecas.oceanologybigdata.model;

public class Tide {
    int id;
    int value;
    String label;
    String tidejson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTidejson() {
        return tidejson;
    }

    public void setTidejson(String tidejson) {
        this.tidejson = tidejson;
    }
}
