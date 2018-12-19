package com.iecas.oceanologybigdata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.iecas.oceanologybigdata.model.Event;
import com.iecas.oceanologybigdata.model.OilSpill;
import com.iecas.oceanologybigdata.model.Tide;
import com.iecas.oceanologybigdata.service.IEventService;
import com.iecas.oceanologybigdata.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class EventController {
    @Autowired
    IEventService eventService;
    @Autowired
    private Environment env;

    @RequestMapping("/inevent")
    @ResponseBody
    int insertEvent(){
        String filepath=env.getProperty("tidePath");
        String jsonStr= FileUtil.ReadFile(filepath);
        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        String label = jsonObject.getString("label");
        int value = jsonObject.getInteger("value");
        List<String> startList=new ArrayList<>();
        List<String> endList=new ArrayList<>();
        JSONArray start=jsonObject.getJSONArray("start");
        JSONArray end=jsonObject.getJSONArray("end");
        for(Object obj:start){
            startList.add(objToStr(obj));
        }
        for(Object obj:end){
            endList.add(objToStr(obj));
        }
        Event event=new Event();
        event.setLabel(label);
        event.setValue(value);
        event.setEventstart(String.join(",",startList));
        event.setEventend(String.join(",",endList));
        eventService.insertEvent(event);
        System.out.println(String.join(",",startList));
        return event.getId();
    }
    @RequestMapping("/intide")
    @ResponseBody
    int insertTide(){
        String filepath=env.getProperty("tidePath");
        String jsonStr= FileUtil.ReadFile(filepath);
        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        String label = jsonObject.getString("label");
        int value = jsonObject.getInteger("value");
        Tide tide=new Tide();
        tide.setValue(value);
        tide.setLabel(label);
        tide.setTidejson(jsonStr);
        eventService.insertTide(tide);
        return tide.getId();
    }
    @RequestMapping("/seltide")
    @ResponseBody
    public List<String> selTide(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        List<String> tideJsonList=eventService.selectTide();
        //Gson gson=new Gson();
        //String json=gson.toJson(tideJsonList);
        return tideJsonList;
    }

    public String objToStr(Object obj){
        JSONObject jsonObjecte = (JSONObject) obj;
        Double lon=jsonObjecte.getDouble("lon");
        Double lat=jsonObjecte.getDouble("lat");
        int height=jsonObjecte.getInteger("height");
        String demo=lon+"|"+lat+"|"+height;
        return demo;
    }

    //解析石油泄漏数据
    @RequestMapping("/seloilspill")
    @ResponseBody
    String selOilSpill(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String oilSpillPath=env.getProperty("oilSpillPath");
        String oilJsonPath=env.getProperty("oilJsonPath");
        File dir=new File(oilSpillPath);
        File[] files=dir.listFiles();
        //解析每一个石油泄漏文件
        for(int ii=0;ii<files.length;ii++) {
            if (files[ii].getName().endsWith("asc")) {
                String fullpath = oilSpillPath + "/" + files[ii].getName();
                String[] oilspillName=files[ii].getName().split("\\.");
                String jsonpath = oilJsonPath + "/" + oilspillName[0]+".json";
                String[] oilName=files[ii].getName().split("_");
                //找出结尾是27.asc的文件
                if(oilName[2].equals("27.asc")){
                    System.out.println(fullpath);
                    List<String> strList = FileUtil.ReadFileToList(fullpath);
                    OilSpill oilSpill=new OilSpill();
                    int ncols=0;
                    int nrows=0;
                    String[][] data=new String[ncols][];
                    for(int i=0;i<strList.size();i++){
                        //System.out.println(strList.get(i));
                        if(i==0){
                            String[] temp=strList.get(i).split("\\s+");
                            ncols=Integer.parseInt(temp[1]);
                            oilSpill.setNcols(ncols);
                            data=new String[ncols][];
                        }else if(i==1){
                            String[] temp=strList.get(i).split("\\s+");
                            nrows=Integer.parseInt(temp[1]);
                            oilSpill.setNrows(nrows);
                        }else if(i==2){
                            String[] temp=strList.get(i).split("\\s+");
                            oilSpill.setXllcorner(Double.parseDouble(temp[1]));
                        } else if(i==3){
                            String[] temp=strList.get(i).split("\\s+");
                            oilSpill.setYllcorner(Double.parseDouble(temp[1]));
                        }else if(i==4){
                            String[] temp=strList.get(i).split("\\s+");
                            oilSpill.setCellsize(Double.parseDouble(temp[1]));
                        }else if(i==5){
                            String[] temp=strList.get(i).split("\\s+");
                            oilSpill.setNODATA(temp[1]);
                        }else {
                            String[] temp=strList.get(i).split("\\s+");
                            data[i-6]=temp;
                            if(i==strList.size()-1){
                                oilSpill.setData(data);
                            }
                        }
                    }
                    System.out.println(oilSpill.getNODATA());
                    //List<SiteInfo> siteInfoList=ctdService.selSite(name);
                    String json = JSON.toJSONString(oilSpill);
                    FileUtil.writeFile(jsonpath,json);
                }
            }

        }




        return "ok";
    }

}
