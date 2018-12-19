package com.iecas.oceanologybigdata.model;

import java.util.Date;

public class FBShzh {
    String time;
    //浊度
    String turbidity;
    //叶绿素
    String chlorophyl;
    //水温
    String waterTemp;
    //盐度
    String salinity;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
