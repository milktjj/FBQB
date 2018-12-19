package com.iecas.oceanologybigdata.model;

public class SailTime {
    int id;
    String sailname;
    String starttime;
    String endtime;

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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
