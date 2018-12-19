package com.iecas.oceanologybigdata.model;

public class Ctd {
    int id;
    String level;
    double depsm;
    double prdm;
    double potemp;
    double sal00;
    double sigma0;
    double sigmat00;
    double sbeox;
    double distance;
    String longitude;
    String latitude;
    int areaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getDepsm() {
        return depsm;
    }

    public void setDepsm(double depsm) {
        this.depsm = depsm;
    }

    public double getPrdm() {
        return prdm;
    }

    public void setPrdm(double prdm) {
        this.prdm = prdm;
    }

    public double getPotemp() {
        return potemp;
    }

    public void setPotemp(double potemp) {
        this.potemp = potemp;
    }

    public double getSal00() {
        return sal00;
    }

    public void setSal00(double sal00) {
        this.sal00 = sal00;
    }

    public double getSigma0() {
        return sigma0;
    }

    public void setSigma0(double sigma0) {
        this.sigma0 = sigma0;
    }

    public double getSigmat00() {
        return sigmat00;
    }

    public void setSigmat00(double sigmat00) {
        this.sigmat00 = sigmat00;
    }

    public double getSbeox() {
        return sbeox;
    }

    public void setSbeox(double sbeox) {
        this.sbeox = sbeox;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
        this.areaid = areaid;
    }
}
