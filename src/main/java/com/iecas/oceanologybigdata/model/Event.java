package com.iecas.oceanologybigdata.model;

public class Event {
    int id;
    int value;
    String label;
    String eventstart;
    String eventend;


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

    public String getEventstart() {
        return eventstart;
    }

    public void setEventstart(String eventstart) {
        this.eventstart = eventstart;
    }

    public String getEventend() {
        return eventend;
    }

    public void setEventend(String eventend) {
        this.eventend = eventend;
    }
}
