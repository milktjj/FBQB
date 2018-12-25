package com.iecas.oceanologybigdata.model;

import com.iecas.oceanologybigdata.util.DeepClone;

import java.io.Serializable;
import java.util.Date;

public class FBQixg extends DeepClone {
    String time;
    String temperature;
    String pressure;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
