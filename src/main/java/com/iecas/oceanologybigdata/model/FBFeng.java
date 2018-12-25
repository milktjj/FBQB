package com.iecas.oceanologybigdata.model;

import com.iecas.oceanologybigdata.util.DeepClone;

import java.io.Serializable;
import java.util.Date;

public class FBFeng extends DeepClone {
    String time;
    //十分钟平均风速
    String avgWindSpeed_10M;
    //十分钟平均风向
    String avgWindDir_10M;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
