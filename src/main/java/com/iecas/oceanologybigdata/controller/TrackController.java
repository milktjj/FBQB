package com.iecas.oceanologybigdata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.google.gson.Gson;
import com.iecas.oceanologybigdata.model.*;
import com.iecas.oceanologybigdata.service.ITrackService;
import com.iecas.oceanologybigdata.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@EnableAutoConfiguration

public class TrackController {
    @Autowired
    private Environment env;
    @Autowired
    private ITrackService trackService;

    @RequestMapping("/intrack")
    @ResponseBody
    int insertTrack(){
        String trackPath=env.getProperty("trackPath");
        String jsonStr= FileUtil.ReadFile(trackPath);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        for(Object obj:jsonArray){
            JSONObject jsonObjecte = (JSONObject) obj;
            String id = jsonObjecte.getString("id");
            String name = jsonObjecte.getString("name");
            String startTime = jsonObjecte.getString("startTime");
            String endTime = jsonObjecte.getString("endTime");
            Track track=new Track();
            track.setId(id);
            track.setName(name);
            track.setStartTime(startTime);
            track.setEndTime(endTime);
            trackService.insertTrack(track);
            System.out.println(track.getNo());
        }

        return 1;
    }
    //查询track数据
    @RequestMapping("/seltrack")
    @ResponseBody
    String selTrack(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        List<TrackData> trackDataList=trackService.selTrack();
        Gson gson=new Gson();
        String json=gson.toJson(trackDataList);
        return json;
    }
    @RequestMapping("/inshiptrack")
    @ResponseBody
    int insertShipTrack(){
        String shiptrackPath=env.getProperty("shiptrackPath");
        String jsonStr= FileUtil.ReadFile(shiptrackPath);
        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        JSONArray voyageStationList=jsonObject.getJSONArray("voyageStationList");
        for(Object obj:voyageStationList) {
            JSONObject jsonObjecte = (JSONObject) obj;
            String trackid=trackId(shiptrackPath);
            String datetime = jsonObjecte.getString("datetime");
            String codeNum = jsonObjecte.getString("codeNum");
            String lat = jsonObjecte.getString("lat");
            String lng = jsonObjecte.getString("lng");
            String station = jsonObjecte.getString("station");
            int metadataId = jsonObjecte.getInteger("metadataId");
            String waterDepth = jsonObjecte.getString("waterDepth");
            int id = jsonObjecte.getInteger("id");
            ShipTrack shipTrack=new ShipTrack();
            shipTrack.setTrackid(trackid);
            shipTrack.setCodeNum(codeNum);
            shipTrack.setDatetime(datetime);
            shipTrack.setVsid(id);
            shipTrack.setLat(lat);
            shipTrack.setLng(lng);
            shipTrack.setMetadataId(metadataId);
            shipTrack.setStation(station);
            shipTrack.setWaterDepth(waterDepth);
            trackService.insertShipTrack(shipTrack);
            System.out.println(shipTrack.getId());
        }

        return 1;
    }
    String trackId(String path){
        String[] track=path.split("/");
        String filename=track[track.length-1];
        String[] id=filename.split("[.]");
        return id[0];
    }
    //查询航迹数据
    @RequestMapping("/selshiptrack")
    @ResponseBody
    String selshiptrack(@RequestParam("trackid")String trackid, HttpServletResponse response){
        System.out.println(trackid);
        response.setHeader("Access-Control-Allow-Origin","*");
        List<ShipTrackData> shipTrackDataList=trackService.selShipTrack(trackid);
        Gson gson=new Gson();
        String json=gson.toJson(shipTrackDataList);
        return json;
    }
    @RequestMapping("/inship")
    @ResponseBody
    int insertShip(){
        String shipPath=env.getProperty("shipPath");
        //System.out.println(shipname(shipPath));
        String jsonStr= FileUtil.ReadFile(shipPath);
        Ship ship=new Ship();
        ship.setShipJson(jsonStr);
        ship.setShipname(shipname(shipPath));
        trackService.insertShip(ship);
        return ship.getId();
    }
    //查询ship数据
    @RequestMapping("/selship")
    @ResponseBody
    String selship(@RequestParam("shipname")String shipname, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        String json=trackService.selShip(shipname);
        return json;
    }
    @RequestMapping("/intrackinfo")
    @ResponseBody
    int insertTrackInfo(){
        String trackinfoPath=env.getProperty("trackinfoPath");
        String jsonStr= FileUtil.ReadFile(trackinfoPath);
        TrackInfo trackInfo=new TrackInfo();
        trackInfo.setInfo(jsonStr);
        trackInfo.setInfoname(shipname(trackinfoPath));
        trackService.insertTrackInfo(trackInfo);
        return trackInfo.getId();
    }
    //查询trackinfo数据
    @RequestMapping("/seltrackinfo")
    @ResponseBody
    String selTrackInfo(@RequestParam("infoname")String infoname, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        String json=trackService.selTrackInfo(infoname);
        return json;
    }

    //sel航迹数据
    @RequestMapping("/selSailTrack")
    @ResponseBody
    String selSailTrack() throws IOException, ParseException {
        String sailPath=env.getProperty("sailPath");
        String stPath=env.getProperty("stPath");
        File dir=new File(sailPath);
        File[] files=dir.listFiles();
        //解析每一个csv文件
        for(int i=0;i<files.length;i++) {
            if (files[i].getName().endsWith("csv")) {
                String fullpath = sailPath + "/" + files[i].getName();
                CsvReader csvReader=new CsvReader(fullpath,',', Charset.forName("GBK"));
                List<SailTrackInfo> sailTrackInfoList=new ArrayList<>();
                // 如果你的文件没有表头，这行不用执行
                // 这行不要是为了从表头的下一行读，也就是过滤表头
                csvReader.readHeaders();
                csvReader.readRecord();
                String sailDate=csvReader.get(3);
                String stName=csvReader.get(0);
                System.out.println(stName);
                String jsonPath=stPath+"/"+stName;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date date=sdf.parse(sailDate);
                // 读取每行的内容
                double lon=0.000;
                double lat=0.000;
                while (csvReader.readRecord()) {
                    // 获取内容的两种方式
                    // 1. 通过下标获取
                    //System.out.print(csvReader.get(0));

                    // 2. 通过表头的文字获取
                    //System.out.println(" " + csvReader.get("年龄"));

                    if(lon!=Double.parseDouble(csvReader.get(0))||lat!=Double.parseDouble(csvReader.get(1))){
                        SailTrackInfo sailTrackInfo=new SailTrackInfo();
                        sailTrackInfo.setLon(Double.parseDouble(csvReader.get(0)));
                        sailTrackInfo.setLat(Double.parseDouble(csvReader.get(1)));
                        sailTrackInfo.setDatetime(sailDate);
                        sailTrackInfoList.add(sailTrackInfo);
                        lon=Double.parseDouble(csvReader.get(0));
                        lat=Double.parseDouble(csvReader.get(1));
                    }
                    date = new Date(date .getTime() + 600000);
                    sailDate=sdf.format(date);
                }
                Gson gson=new Gson();
                String json=gson.toJson(sailTrackInfoList);
                FileUtil.writeFile(jsonPath,json);
            }
        }


        return "ok";
    }

    //in航迹数据
    //in航迹时间数据
    @RequestMapping("/inSailTrack")
    @ResponseBody
    int inSailTrack() throws IOException, ParseException {
        String sailPath=env.getProperty("sailPath");
        String stPath=env.getProperty("stPath");
        File dir=new File(sailPath);
        File[] files=dir.listFiles();
        //解析每一个csv文件
        for(int i=0;i<files.length;i++) {
            if (files[i].getName().endsWith("csv")) {
                SailTime sailTime=new SailTime();
                String endTime="";
                String fullpath = sailPath + "/" + files[i].getName();
                CsvReader csvReader=new CsvReader(fullpath,',', Charset.forName("GBK"));
                // 如果你的文件没有表头，这行不用执行
                // 这行不要是为了从表头的下一行读，也就是过滤表头
                csvReader.readHeaders();
                csvReader.readRecord();
                String sailDate=csvReader.get(3);
                String stName=csvReader.get(0);
                sailTime.setSailname(stName);
                sailTime.setStarttime(sailDate);
                System.out.println(stName);
                String jsonPath=stPath+"/"+stName;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date date=sdf.parse(sailDate);
                // 读取每行的内容
                double lon=0.000;
                double lat=0.000;
                while (csvReader.readRecord()) {
                    // 获取内容的两种方式
                    // 1. 通过下标获取
                    //System.out.print(csvReader.get(0));

                    // 2. 通过表头的文字获取
                    //System.out.println(" " + csvReader.get("年龄"));
                    if(lon!=Double.parseDouble(csvReader.get(0))||lat!=Double.parseDouble(csvReader.get(1))){
                        SailTrack sailTrackInfo=new SailTrack();
                        sailTrackInfo.setLon(Double.parseDouble(csvReader.get(0)));
                        sailTrackInfo.setLat(Double.parseDouble(csvReader.get(1)));
                        sailTrackInfo.setDatetime(sailDate);
                        sailTrackInfo.setSailname(stName);
                        //trackService.insertSailTrack(sailTrackInfo);
                        //System.out.println(sailTrackInfo.getId());
                        lon=Double.parseDouble(csvReader.get(0));
                        lat=Double.parseDouble(csvReader.get(1));
                        endTime=sailDate;
                    }
                    date = new Date(date .getTime() + 600000);
                    sailDate=sdf.format(date);
                }
                sailTime.setEndtime(endTime);
                trackService.insertSailTrackTime(sailTime);
            }
        }
        return 1;
    }

    String shipname(String path){
        String[] track=path.split("/");
        String filename=track[track.length-1];
        String[] id=filename.split("[.]");
        return id[0];
    }
}
