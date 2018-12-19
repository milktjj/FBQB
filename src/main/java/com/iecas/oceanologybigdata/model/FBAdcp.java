package com.iecas.oceanologybigdata.model;

import java.sql.Timestamp;
import java.util.Date;

public class FBAdcp {
    String time;
    String flowRate1;
    String  flowDir1;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
