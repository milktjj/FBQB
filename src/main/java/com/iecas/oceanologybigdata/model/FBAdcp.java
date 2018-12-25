package com.iecas.oceanologybigdata.model;

import com.iecas.oceanologybigdata.util.DeepClone;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class FBAdcp extends DeepClone {
    String time;
    String flowRate1;
    String  flowDir1;
    Boolean isCache;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
