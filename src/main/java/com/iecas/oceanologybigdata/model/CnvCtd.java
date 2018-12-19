package com.iecas.oceanologybigdata.model;

public class CnvCtd {
    int id;
    String name;
    double times;
    int pumps;
    double prdm;
    double depsm;
    double temperature;
    double dz;
    double sbeox;
    double conductivity;
    double svcm;
    double density;
    double salinity;
    double latitude;
    double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTimes() {
        return times;
    }

    public void setTimes(double times) {
        this.times = times;
    }

    public int getPumps() {
        return pumps;
    }

    public void setPumps(int pumps) {
        this.pumps = pumps;
    }

    public double getPrdm() {
        return prdm;
    }

    public void setPrdm(double prdm) {
        this.prdm = prdm;
    }

    public double getDepsm() {
        return depsm;
    }

    public void setDepsm(double depsm) {
        this.depsm = depsm;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getDz() {
        return dz;
    }

    public void setDz(double dz) {
        this.dz = dz;
    }

    public double getSbeox() {
        return sbeox;
    }

    public void setSbeox(double sbeox) {
        this.sbeox = sbeox;
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    public double getSvcm() {
        return svcm;
    }

    public void setSvcm(double svcm) {
        this.svcm = svcm;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getSalinity() {
        return salinity;
    }

    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
