package com.iecas.oceanologybigdata.controller;

import com.google.gson.Gson;
import com.iecas.oceanologybigdata.Redis.RedisUtils;
import com.iecas.oceanologybigdata.model.QBAdcp;
import com.iecas.oceanologybigdata.model.QBAqd;
import com.iecas.oceanologybigdata.model.QBCtd;
import com.iecas.oceanologybigdata.model.QBInfo;
import com.iecas.oceanologybigdata.service.impl.QBService;
import com.iecas.oceanologybigdata.util.Datecache;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@Slf4j
public class QBController {
    @Autowired
    QBService qbService;
    @Autowired
    RedisUtils jedis;

    private static final Logger logger = LoggerFactory.getLogger(QBController.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    List t = new ArrayList();

    @GetMapping("/qbInfos")
    public String getQBInfos() {
        if (jedis.hasKey("qbInfos")) {
            return jedis.get("qbInfos").toString();
        } else {
            String json = new Gson().toJson(qbService.getQBInfos());
            return json;
        }

    }

    @GetMapping(value = "/qbInfos/{id}", produces = "application/json;charset=UTF-8")
    public String getQBInfoById(@PathVariable int id) throws ParseException {
        if (jedis.hasKey("getQBInfoById+" + id)) {
            return jedis.get("getQBInfoById+" + id).toString();
        } else {
            QBInfo qbInfo = qbService.getQBInfoByID(id);
            if (qbInfo != null) {
                List<QBAqd> qbAqds = qbService.getQBAqdsByLL(qbInfo.getLon() + "," + qbInfo.getLat());
                if (qbAqds.size() > 0) {
                    for (QBAqd qbAqd : qbAqds) {
                        qbAqd.setTime(timeFormat.format(qbAqd.getTimestamp()));
                        qbAqd.setDate(dateFormat.format(qbAqd.getTimestamp()));
                        qbAqd.setDateTime(dateTimeFormat.format(qbAqd.getTimestamp()));
                        qbAqd.setTimestamp(null);
                    }
                }
                qbInfo.setQbAqds(qbAqds);

                List<QBCtd> qbCtds = qbService.getQBCtdsByLL(qbInfo.getLon() + "," + qbInfo.getLat());
                if (qbCtds.size() > 0) {
                    for (QBCtd qbCtd : qbCtds) {
                        qbCtd.setTime(timeFormat.format(qbCtd.getTimestamp()));
                        qbCtd.setDate(dateFormat.format(qbCtd.getTimestamp()));
                        qbCtd.setDateTime(dateTimeFormat.format(qbCtd.getTimestamp()));
                        qbCtd.setTimestamp(null);
                    }
                }
                qbInfo.setQbCtds(qbCtds);

                List<QBAdcp> qbAdcps = qbService.getQBAdcpsByLL(qbInfo.getLon() + "," + qbInfo.getLat());
                System.out.println(qbAdcps.size());
                if (qbAdcps.size() > 0) {
                    for (QBAdcp qbAdcp : qbAdcps) {
                        qbAdcp.setTime(timeFormat.format(qbAdcp.getTimestamp()));
                        qbAdcp.setDate(dateFormat.format(qbAdcp.getTimestamp()));
                        qbAdcp.setDateTime(dateTimeFormat.format(qbAdcp.getTimestamp()));
                        qbAdcp.setTimestamp(null);
                    }
                }


                qbInfo.setQbAdcps(qbAdcps);
                qbInfo.setmm();
            } else
                return "[]";
            String json = new Gson().toJson(qbInfo);
            return json;
        }
    }

    @GetMapping(value = "/qbInfos/csv/{id}")
    public String getQBInfoByIdCsv(@PathVariable int id) throws ParseException {

        QBInfo qbInfo = qbService.getQBInfoByID(id);

        List<QBAqd> qbAqds = qbService.getQBAqdsByLL(qbInfo.getLon() + "," + qbInfo.getLat());
        if (qbAqds != null) {
            for (QBAqd qbAqd : qbAqds) {
                qbAqd.setTime(timeFormat.format(qbAqd.getTimestamp()));
                qbAqd.setDate(dateFormat.format(qbAqd.getTimestamp()));
                qbAqd.setDateTime(dateTimeFormat.format(qbAqd.getTimestamp()));
                qbAqd.setTimestamp(null);
            }
        }
        //qbInfo.setQbAqds(qbAqds);
        StringBuilder csv = new StringBuilder();
        csv.append("date,time,cvSpeed</br>");
        for (QBAqd qbAqd : qbAqds) {
            csv.append(qbAqd.getDate());
            csv.append(',');
            csv.append(qbAqd.getTime());
            csv.append(',');
            csv.append(qbAqd.getCvSpeed());
            csv.append("</br>");
        }

       /* List<QBCtd> qbCtds = qbService.getQBCtdsByLL(qbInfo.getLon()+","+qbInfo.getLat());
        if(qbCtds != null) {
            for (QBCtd qbCtd : qbCtds) {
                qbCtd.setTime(timeFormat.format(qbCtd.getTimestamp()));
                qbCtd.setDate(dateFormat.format(qbCtd.getTimestamp()));
                qbCtd.setDateTime(dateTimeFormat.format(qbCtd.getTimestamp()));
                qbCtd.setTimestamp(null);
            }
        }
        qbInfo.setQbCtds(qbCtds);

        List<QBAdcp> qbAdcps = qbService.getQBAdcpsByLL(qbInfo.getLon()+","+qbInfo.getLat());
        System.out.println(qbAdcps.size());
        if(qbAdcps != null) {
            for (QBAdcp qbAdcp : qbAdcps) {
                qbAdcp.setTime(timeFormat.format(qbAdcp.getTimestamp()));
                qbAdcp.setDate(dateFormat.format(qbAdcp.getTimestamp()));
                qbAdcp.setDateTime(dateTimeFormat.format(qbAdcp.getTimestamp()));
                qbAdcp.setTimestamp(null);
            }
        }


        qbInfo.setQbAdcps(qbAdcps);

        String json = new Gson().toJson(qbInfo);*/
        return csv.toString();
    }


}
