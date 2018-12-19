package com.iecas.oceanologybigdata.controller;

import com.google.gson.Gson;
import com.iecas.oceanologybigdata.model.*;
import com.iecas.oceanologybigdata.service.impl.FBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class FBController {
    @Autowired
    FBService fbService;
    @GetMapping(value = "/fbInfos/{id}", produces = "application/json;charset=UTF-8")
    public String getFbinfoById(@PathVariable String id, HttpServletResponse response) throws ParseException {
        response.setHeader("Access-Control-Allow-Origin", "*");

        FBInfo fb = fbService.getFBInfoById(id);
        if (fb != null) {
            List<FBAdcp> fbAdcps = fbService.getFBAdcpsById(id, "18111");
            if (fbAdcps.size() > 0) {
                for (FBAdcp adcp : fbAdcps)
                    adcp.setTime(convertTime(adcp.getTime()));
                fb.setFbAdcps(fbAdcps);
            }

            List<FBBlog> fbBlogs = fbService.getFBBlogsById(id, "18111");
            if (fbBlogs.size() > 0) {
                for (FBBlog fbBlog : fbBlogs)
                    fbBlog.setTime(convertTime(fbBlog.getTime()));
                fb.setFbBlogs(fbBlogs);
            }

            List<FBShzh> fbShzhs = fbService.getFBShzhsById(id, "18111");
            if (fbShzhs.size() > 0) {
                for (FBShzh fbShzh : fbShzhs)
                    fbShzh.setTime(convertTime(fbShzh.getTime()));
                fb.setFbShzhs(fbShzhs);
            }

            List<FBQixg> fbQixgs = fbService.getFBQixgById(id, "18111");
            if (fbQixgs.size() > 0) {
                for (FBQixg fbQixg : fbQixgs)
                    fbQixg.setTime(convertTime(fbQixg.getTime()));
                fb.setFbQixgs(fbQixgs);
            }
            List<FBFeng> fbFengs = fbService.getFBFengsById(id, "18111");
            if (fbFengs.size() > 0) {
                for (FBFeng fbFeng : fbFengs)
                    fbFeng.setTime(convertTime(fbFeng.getTime()));
                fb.setFbFengs(fbFengs);
            }
        }else
            return "[]";
        return new Gson().toJson(fb);
    }

    @GetMapping("/fbInfos")
    public String getFbinfos() {
        return new Gson().toJson(fbService.getFBInfos());
    }

    /*public Date convertTime(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.insert(2, )
        SimpleDateFormat format =  new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String d = format.format(timestamp);
        Date date=format.parse(d);
        System.out.println("Format To String(Date):"+d);
        System.out.println("Format To Date:"+date);

        return
    }*/

    public static String convertTime(String time) throws ParseException {
        StringBuilder sb = new StringBuilder(time);
        sb.insert(0, "20");
        sb.insert(4, '/');
        sb.insert(7, '/');
        sb.insert(10, ' ');
        sb.insert(13, ':');

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = dateFormat.parse(sb.toString());

        return dateFormat.format(date);
    }

}
