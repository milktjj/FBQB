package com.iecas.oceanologybigdata.model;

import java.util.Date;

public class FBBlog {
    String time;
    String validWaveHeight;
    String maxWaveHeight;
    String avgWaveHeight;
    String validWaveCycle;
    String maxWaveCycle;
    String avgWaveCycle;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
