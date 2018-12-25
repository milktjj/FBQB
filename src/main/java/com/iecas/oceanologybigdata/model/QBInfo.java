package com.iecas.oceanologybigdata.model;

import com.iecas.oceanologybigdata.util.DeepClone;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QBInfo extends DeepClone {
    int qbId;
    double lon;
    double lat;
    double depth;
    double aqds_maxCuSpeed;
    double aqds_maxCvSpeed;
    double aqds_maxCwSpeed;
    double aqds_maxPressure;

    double adcp_maxCu;
    double adcp_maxCv;
    double adcp_maxDepth;

    double ctd_maxTemperature;
    double ctd_maxSality;
    double ctd_maxPressure;

    double aqds_minCuSpeed;
    double aqds_minCvSpeed;
    double aqds_minCwSpeed;
    double aqds_minPressure;

    double adcp_minCu;
    double adcp_minCv;
    double adcp_minDepth;

    double ctd_minTemperature;
    double ctd_minSality;
    double ctd_minPressure;


    List<QBAqd> qbAqds;

    List<QBCtd> qbCtds;
    List<QBAdcp> qbAdcps;


    public void setmm() {
        if (qbAqds.size() > 0) {
            String aqd_Cu = getAqds_CuSpeed();
            String aqd_Cv = getAqds_CvSpeed();
            String aqd_Cw = getAqds_CwSpeed();
            String aqd_pre = getAqds_Pressure();
            setAqds_minCuSpeed(Double.parseDouble(aqd_Cu.split(";")[0]));
            setAqds_maxCuSpeed(Double.parseDouble(aqd_Cu.split(";")[1]));
            setAqds_minCvSpeed(Double.parseDouble(aqd_Cv.split(";")[0]));
            setAqds_maxCvSpeed(Double.parseDouble(aqd_Cv.split(";")[1]));
            setAqds_minCwSpeed(Double.parseDouble(aqd_Cw.split(";")[0]));
            setAqds_maxCwSpeed(Double.parseDouble(aqd_Cw.split(";")[1]));
            setAqds_minPressure(Double.parseDouble(aqd_pre.split(";")[0]));
            setAqds_maxPressure(Double.parseDouble(aqd_pre.split(";")[1]));
        }
        if (qbAdcps.size() > 0) {
            String adcp_Cu = getAdcp_Cu();
            String adcp_Cv = getAdcp_Cv();
            String adcp_Depth = getAdcp_Depth();
            setAdcp_minCu(Double.parseDouble(adcp_Cu.split(";")[0]));
            setAdcp_maxCu(Double.parseDouble(adcp_Cu.split(";")[1]));
            setAdcp_minCv(Double.parseDouble(adcp_Cv.split(";")[0]));
            setAdcp_maxCv(Double.parseDouble(adcp_Cv.split(";")[1]));
            setAdcp_minDepth(Double.parseDouble(adcp_Depth.split(";")[0]));
            setAdcp_maxDepth(Double.parseDouble(adcp_Depth.split(";")[1]));
        }
        if (qbCtds.size() > 0) {
            String ctd_Temp = getCtd_Temperature();
            String ctd_Sal = getCtd_Sality();
            String ctd_Pre = getCtd_Pressure();
            setCtd_minTemperature(Double.parseDouble(ctd_Temp.split(";")[0]));
            setCtd_maxTemperature(Double.parseDouble(ctd_Temp.split(";")[1]));
            setCtd_minSality(Double.parseDouble(ctd_Sal.split(";")[0]));
            setCtd_maxSality(Double.parseDouble(ctd_Sal.split(";")[1]));
            setCtd_minPressure(Double.parseDouble(ctd_Pre.split(";")[0]));
            setCtd_maxPressure(Double.parseDouble(ctd_Pre.split(";")[1]));
        }
        //System.out.println(ctd_Pre + " " + ctd_Sal);

    }

    public String getAqds_CuSpeed() {
        List aqdCuSpeed = new ArrayList();
        for (QBAqd aqd : qbAqds) {
            if (aqd.cuSpeed != 9999)
                aqdCuSpeed.add(aqd.cuSpeed);

        }
        Collections.sort(aqdCuSpeed);
        System.out.println(aqdCuSpeed.get(1));
        System.out.println(aqdCuSpeed.get(2));
        return Collections.min(aqdCuSpeed) + ";" + Collections.max(aqdCuSpeed);
    }

    public void setAqds_minCuSpeed(double aqds_minCuSpeed) {
        this.aqds_minCuSpeed = aqds_minCuSpeed;
    }

    public String getAqds_CvSpeed() {
        List aqdCvSpeed = new ArrayList();
        for (QBAqd aqd : qbAqds) {
            if (aqd.cvSpeed != 9999)
                aqdCvSpeed.add(aqd.cvSpeed);
        }
        Collections.sort(aqdCvSpeed);
        System.out.println("cv" + aqdCvSpeed.get(1));
        System.out.println("cv" + aqdCvSpeed.get(2));
        return Collections.min(aqdCvSpeed) + ";" + Collections.max(aqdCvSpeed);
    }

    public void setAqds_minCvSpeed(double aqds_minCvSpeed) {
        this.aqds_minCvSpeed = aqds_minCvSpeed;
    }

    public String getAqds_CwSpeed() {
        List aqdCwSpeed = new ArrayList();
        for (QBAqd aqd : qbAqds) {
            if (aqd.cwSpeed != 9999)
                aqdCwSpeed.add(aqd.cwSpeed);
        }
        Collections.sort(aqdCwSpeed);
        System.out.println(aqdCwSpeed.get(1));
        System.out.println(aqdCwSpeed.get(2));
        return (double) Collections.min(aqdCwSpeed) + ";" + (double) Collections.max(aqdCwSpeed);
    }

    public void setAqds_minCwSpeed(double aqds_minCwSpeed) {
        this.aqds_minCwSpeed = aqds_minCwSpeed;
    }

    public String getAqds_Pressure() {

        List aqdPressure = new ArrayList();
        for (QBAqd aqd : qbAqds) {
            if (aqd.pressure != 9999)
                aqdPressure.add(aqd.pressure);
        }

        return (double) Collections.min(aqdPressure) + ";" + (double) Collections.max(aqdPressure);
    }

    public void setAqds_minPressure(double aqds_minPressure) {
        this.aqds_minPressure = aqds_minPressure;
    }

    public String getAdcp_Cu() {
        List adcpCu = new ArrayList();
        for (QBAdcp adcp : qbAdcps) {
            if (adcp.cu1 != 9999) adcpCu.add(adcp.cu1);
            if (adcp.cu2 != 9999) adcpCu.add(adcp.cu2);
            if (adcp.cu3 != 9999) adcpCu.add(adcp.cu3);
            if (adcp.cu4 != 9999) adcpCu.add(adcp.cu4);
            if (adcp.cu5 != 9999) adcpCu.add(adcp.cu5);
            if (adcp.cu6 != 9999) adcpCu.add(adcp.cu6);
            if (adcp.cu7 != 9999) adcpCu.add(adcp.cu7);
            if (adcp.cu8 != 9999) adcpCu.add(adcp.cu8);
            if (adcp.cu9 != 9999) adcpCu.add(adcp.cu9);
            if (adcp.cu10 != 9999) adcpCu.add(adcp.cu10);
            if (adcp.cu11 != 9999) adcpCu.add(adcp.cu11);
            if (adcp.cu12 != 9999) adcpCu.add(adcp.cu12);
            if (adcp.cu13 != 9999) adcpCu.add(adcp.cu13);
            if (adcp.cu14 != 9999) adcpCu.add(adcp.cu14);
            if (adcp.cu15 != 9999) adcpCu.add(adcp.cu15);
            if (adcp.cu16 != 9999) adcpCu.add(adcp.cu16);
            if (adcp.cu17 != 9999) adcpCu.add(adcp.cu17);
            if (adcp.cu18 != 9999) adcpCu.add(adcp.cu18);
            if (adcp.cu19 != 9999) adcpCu.add(adcp.cu19);
            if (adcp.cu20 != 9999) adcpCu.add(adcp.cu20);
            if (adcp.cu21 != 9999) adcpCu.add(adcp.cu21);
            if (adcp.cu22 != 9999) adcpCu.add(adcp.cu22);
            if (adcp.cu23 != 9999) adcpCu.add(adcp.cu23);
            if (adcp.cu24 != 9999) adcpCu.add(adcp.cu24);
            if (adcp.cu25 != 9999) adcpCu.add(adcp.cu25);
            if (adcp.cu26 != 9999) adcpCu.add(adcp.cu26);
            if (adcp.cu27 != 9999) adcpCu.add(adcp.cu27);
            if (adcp.cu28 != 9999) adcpCu.add(adcp.cu28);
            if (adcp.cu29 != 9999) adcpCu.add(adcp.cu29);
            if (adcp.cu30 != 9999) adcpCu.add(adcp.cu30);
            if (adcp.cu31 != 9999) adcpCu.add(adcp.cu31);
            if (adcp.cu32 != 9999) adcpCu.add(adcp.cu32);
            if (adcp.cu33 != 9999) adcpCu.add(adcp.cu33);
            if (adcp.cu34 != 9999) adcpCu.add(adcp.cu34);
            if (adcp.cu35 != 9999) adcpCu.add(adcp.cu35);
            if (adcp.cu36 != 9999) adcpCu.add(adcp.cu36);
            if (adcp.cu37 != 9999) adcpCu.add(adcp.cu37);
            if (adcp.cu38 != 9999) adcpCu.add(adcp.cu38);
            if (adcp.cu39 != 9999) adcpCu.add(adcp.cu39);
            if (adcp.cu40 != 9999) adcpCu.add(adcp.cu40);
            if (adcp.cu41 != 9999) adcpCu.add(adcp.cu41);
            if (adcp.cu42 != 9999) adcpCu.add(adcp.cu42);
            if (adcp.cu43 != 9999) adcpCu.add(adcp.cu43);
            if (adcp.cu44 != 9999) adcpCu.add(adcp.cu44);
            if (adcp.cu45 != 9999) adcpCu.add(adcp.cu45);
            if (adcp.cu46 != 9999) adcpCu.add(adcp.cu46);
            if (adcp.cu47 != 9999) adcpCu.add(adcp.cu47);
            if (adcp.cu48 != 9999) adcpCu.add(adcp.cu48);
            if (adcp.cu49 != 9999) adcpCu.add(adcp.cu49);
            if (adcp.cu50 != 9999) adcpCu.add(adcp.cu50);
            if (adcp.cu51 != 9999) adcpCu.add(adcp.cu51);
            if (adcp.cu52 != 9999) adcpCu.add(adcp.cu52);
            if (adcp.cu53 != 9999) adcpCu.add(adcp.cu53);
            if (adcp.cu54 != 9999) adcpCu.add(adcp.cu54);
            if (adcp.cu55 != 9999) adcpCu.add(adcp.cu55);
            if (adcp.cu56 != 9999) adcpCu.add(adcp.cu56);
            if (adcp.cu57 != 9999) adcpCu.add(adcp.cu57);
            if (adcp.cu58 != 9999) adcpCu.add(adcp.cu58);
            if (adcp.cu59 != 9999) adcpCu.add(adcp.cu59);
            if (adcp.cu60 != 9999) adcpCu.add(adcp.cu60);

        }


        return (double) Collections.min(adcpCu) + ";" + (double) Collections.max(adcpCu);
    }

    public void setAdcp_minCu(double adcp_minCu) {
        this.adcp_minCu = adcp_minCu;
    }

    public String getAdcp_Cv() {
        List adcpCv = new ArrayList();
        for (QBAdcp adcp : qbAdcps) {
            if (adcp.cv1 != 9999) adcpCv.add(adcp.cv1);
            if (adcp.cv2 != 9999) adcpCv.add(adcp.cv2);
            if (adcp.cv3 != 9999) adcpCv.add(adcp.cv3);
            if (adcp.cv4 != 9999) adcpCv.add(adcp.cv4);
            if (adcp.cv5 != 9999) adcpCv.add(adcp.cv5);
            if (adcp.cv6 != 9999) adcpCv.add(adcp.cv6);
            if (adcp.cv7 != 9999) adcpCv.add(adcp.cv7);
            if (adcp.cv8 != 9999) adcpCv.add(adcp.cv8);
            if (adcp.cv9 != 9999) adcpCv.add(adcp.cv9);
            if (adcp.cv10 != 9999) adcpCv.add(adcp.cv10);
            if (adcp.cv11 != 9999) adcpCv.add(adcp.cv11);
            if (adcp.cv12 != 9999) adcpCv.add(adcp.cv12);
            if (adcp.cv13 != 9999) adcpCv.add(adcp.cv13);
            if (adcp.cv14 != 9999) adcpCv.add(adcp.cv14);
            if (adcp.cv15 != 9999) adcpCv.add(adcp.cv15);
            if (adcp.cv16 != 9999) adcpCv.add(adcp.cv16);
            if (adcp.cv17 != 9999) adcpCv.add(adcp.cv17);
            if (adcp.cv18 != 9999) adcpCv.add(adcp.cv18);
            if (adcp.cv19 != 9999) adcpCv.add(adcp.cv19);
            if (adcp.cv20 != 9999) adcpCv.add(adcp.cv20);
            if (adcp.cv21 != 9999) adcpCv.add(adcp.cv21);
            if (adcp.cv22 != 9999) adcpCv.add(adcp.cv22);
            if (adcp.cv23 != 9999) adcpCv.add(adcp.cv23);
            if (adcp.cv24 != 9999) adcpCv.add(adcp.cv24);
            if (adcp.cv25 != 9999) adcpCv.add(adcp.cv25);
            if (adcp.cv26 != 9999) adcpCv.add(adcp.cv26);
            if (adcp.cv27 != 9999) adcpCv.add(adcp.cv27);
            if (adcp.cv28 != 9999) adcpCv.add(adcp.cv28);
            if (adcp.cv29 != 9999) adcpCv.add(adcp.cv29);
            if (adcp.cv30 != 9999) adcpCv.add(adcp.cv30);
            if (adcp.cv31 != 9999) adcpCv.add(adcp.cv31);
            if (adcp.cv32 != 9999) adcpCv.add(adcp.cv32);
            if (adcp.cv33 != 9999) adcpCv.add(adcp.cv33);
            if (adcp.cv34 != 9999) adcpCv.add(adcp.cv34);
            if (adcp.cv35 != 9999) adcpCv.add(adcp.cv35);
            if (adcp.cv36 != 9999) adcpCv.add(adcp.cv36);
            if (adcp.cv37 != 9999) adcpCv.add(adcp.cv37);
            if (adcp.cv38 != 9999) adcpCv.add(adcp.cv38);
            if (adcp.cv39 != 9999) adcpCv.add(adcp.cv39);
            if (adcp.cv40 != 9999) adcpCv.add(adcp.cv40);
            if (adcp.cv41 != 9999) adcpCv.add(adcp.cv41);
            if (adcp.cv42 != 9999) adcpCv.add(adcp.cv42);
            if (adcp.cv43 != 9999) adcpCv.add(adcp.cv43);
            if (adcp.cv44 != 9999) adcpCv.add(adcp.cv44);
            if (adcp.cv45 != 9999) adcpCv.add(adcp.cv45);
            if (adcp.cv46 != 9999) adcpCv.add(adcp.cv46);
            if (adcp.cv47 != 9999) adcpCv.add(adcp.cv47);
            if (adcp.cv48 != 9999) adcpCv.add(adcp.cv48);
            if (adcp.cv49 != 9999) adcpCv.add(adcp.cv49);
            if (adcp.cv50 != 9999) adcpCv.add(adcp.cv50);
            if (adcp.cv51 != 9999) adcpCv.add(adcp.cv51);
            if (adcp.cv52 != 9999) adcpCv.add(adcp.cv52);
            if (adcp.cv53 != 9999) adcpCv.add(adcp.cv53);
            if (adcp.cv54 != 9999) adcpCv.add(adcp.cv54);
            if (adcp.cv55 != 9999) adcpCv.add(adcp.cv55);
            if (adcp.cv56 != 9999) adcpCv.add(adcp.cv56);
            if (adcp.cv57 != 9999) adcpCv.add(adcp.cv57);
            if (adcp.cv58 != 9999) adcpCv.add(adcp.cv58);
            if (adcp.cv59 != 9999) adcpCv.add(adcp.cv59);
            if (adcp.cv60 != 9999) adcpCv.add(adcp.cv60);
        }

        return (double) Collections.min(adcpCv) + ";" + (double) Collections.max(adcpCv);
    }

    public void setAdcp_minCv(double adcp_minCv) {
        this.adcp_minCv = adcp_minCv;
    }

    public String getAdcp_Depth() {
        List adcpDepth = new ArrayList();
        for (QBAdcp adcp : qbAdcps) {
            if (adcp.depth != 9999)
                adcpDepth.add(adcp.depth);
        }

        return (double) Collections.min(adcpDepth) + ";" + (double) Collections.max(adcpDepth);
    }

    public void setAdcp_minDepth(double adcp_minDepth) {
        this.adcp_minDepth = adcp_minDepth;
    }

    public String getCtd_Temperature() {
        List ctdTemp = new ArrayList();
        for (QBCtd ctd : qbCtds) {
            if (ctd.temperature != 9999)
                ctdTemp.add(ctd.temperature);
        }

        return (double) Collections.min(ctdTemp) + ";" + (double) Collections.max(ctdTemp);
    }

    public void setCtd_minTemperature(double ctd_minTemperature) {
        this.ctd_minTemperature = ctd_minTemperature;
    }

    public String getCtd_Sality() {
        List ctdSal = new ArrayList();
        for (QBCtd ctd : qbCtds) {
            if (ctd.sality != 9999)
                ctdSal.add(ctd.sality);
        }

        return (double) Collections.min(ctdSal) + ";" + (double) Collections.max(ctdSal);
    }

    public void setCtd_minSality(double ctd_minSality) {
        this.ctd_minSality = ctd_minSality;
    }

    public String getCtd_Pressure() {
        List ctdPressure = new ArrayList();
        for (QBCtd ctd : qbCtds) {
            if (ctd.pressure != 9999)
                ctdPressure.add(ctd.pressure);
        }

        return (double) Collections.min(ctdPressure) + ";" + (double) Collections.max(ctdPressure);
    }

    public void setCtd_minPressure(double ctd_minPressure) {
        this.ctd_minPressure = ctd_minPressure;
    }

    public double getAqds_maxCuSpeed() {

        return aqds_maxCuSpeed;
    }

    public void setAqds_maxCuSpeed(double aqds_maxCuSpeed) {
        this.aqds_maxCuSpeed = aqds_maxCuSpeed;
    }

    public double getAqds_maxCvSpeed() {
        return aqds_maxCvSpeed;
    }

    public void setAqds_maxCvSpeed(double aqds_maxCvSpeed) {
        this.aqds_maxCvSpeed = aqds_maxCvSpeed;
    }

    public double getAqds_maxCwSpeed() {
        return aqds_maxCwSpeed;
    }

    public void setAqds_maxCwSpeed(double aqds_maxCwSpeed) {
        this.aqds_maxCwSpeed = aqds_maxCwSpeed;
    }

    public double getAqds_maxPressure() {
        return aqds_maxPressure;
    }

    public void setAqds_maxPressure(double aqds_maxPressure) {
        this.aqds_maxPressure = aqds_maxPressure;
    }

    public double getAdcp_maxCu() {
        return adcp_maxCu;
    }

    public void setAdcp_maxCu(double adcp_maxCu) {
        this.adcp_maxCu = adcp_maxCu;
    }

    public double getAdcp_maxCv() {
        return adcp_maxCv;
    }

    public void setAdcp_maxCv(double adcp_maxCv) {
        this.adcp_maxCv = adcp_maxCv;
    }

    public double getAdcp_maxDepth() {
        return adcp_maxDepth;
    }

    public void setAdcp_maxDepth(double adcp_maxDepth) {
        this.adcp_maxDepth = adcp_maxDepth;
    }

    public double getCtd_maxTemperature() {
        return ctd_maxTemperature;
    }

    public void setCtd_maxTemperature(double ctd_maxTemperature) {
        this.ctd_maxTemperature = ctd_maxTemperature;
    }

    public double getCtd_maxSality() {
        return ctd_maxSality;
    }

    public void setCtd_maxSality(double ctd_maxSality) {
        this.ctd_maxSality = ctd_maxSality;
    }

    public double getCtd_maxPressure() {
        return ctd_maxPressure;
    }

    public void setCtd_maxPressure(double ctd_maxPressure) {
        this.ctd_maxPressure = ctd_maxPressure;
    }


    public List<QBAdcp> getQbAdcps() {
        return qbAdcps;
    }

    public void setQbAdcps(List<QBAdcp> qbAdcps) {
        this.qbAdcps = qbAdcps;
    }

    public List<QBAqd> getQbAqds() {
        return qbAqds;
    }

    public void setQbAqds(List<QBAqd> qbAqds) {
        this.qbAqds = qbAqds;
    }

    public List<QBCtd> getQbCtds() {
        return qbCtds;
    }

    public void setQbCtds(List<QBCtd> qbCtds) {
        this.qbCtds = qbCtds;
    }

    public int getQbId() {
        return qbId;
    }

    public void setQbId(int fbId) {
        this.qbId = fbId;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }


}