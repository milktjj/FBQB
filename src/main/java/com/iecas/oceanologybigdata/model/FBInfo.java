package com.iecas.oceanologybigdata.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FBInfo {
    String fbId;
    double lon;
    double lat;
    String description;
    double depth;
    String icon;
    String adcp_minFlowRate1;
    String adcp_maxFlowRate1;
    String adcp_minFlowDir1;
    String adcp_maxFlowDir1;
    List<FBAdcp> fbAdcps;
    List<FBBlog> fbBlogs;
    List<FBShzh> fbShzhs;
    List<FBQixg> fbQixgs;
    List<FBFeng> fbFengs;







    public List<FBFeng> getFbFengs() {
        return fbFengs;
    }

    public void setFbFengs(List<FBFeng> fbFengs) {
        this.fbFengs = fbFengs;
    }

    public List<FBQixg> getFbQixgs() {
        return fbQixgs;
    }

    public void setFbQixgs(List<FBQixg> fbQixgs) {
        this.fbQixgs = fbQixgs;
    }

    public List<FBShzh> getFbShzhs() {
        return fbShzhs;
    }

    public void setFbShzhs(List<FBShzh> fbShzhs) {
        this.fbShzhs = fbShzhs;
    }

    public List<FBBlog> getFbBlogs() {
        return fbBlogs;
    }

    public void setFbBlogs(List<FBBlog> fbBlogs) {
        this.fbBlogs = fbBlogs;
    }

    public List<FBAdcp> getFbAdcps() {
        return fbAdcps;
    }

    public void setFbAdcps(List<FBAdcp> fbAdcps) {
        this.fbAdcps = fbAdcps;
    }


}
