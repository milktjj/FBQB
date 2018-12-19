package com.iecas.oceanologybigdata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.iecas.oceanologybigdata.model.*;
import com.iecas.oceanologybigdata.service.ICtdService;
import com.iecas.oceanologybigdata.util.CtdUtil;
import com.iecas.oceanologybigdata.util.DbfUtil;
import com.iecas.oceanologybigdata.util.FileUtil;
import com.iecas.oceanologybigdata.util.PostgresTest;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class CtdController {
    @Autowired
    private Environment env;
    @Autowired
    private ICtdService ctdService;


    //解析cnv文件获取ctd数据
//    @RequestMapping("/incnvctd")
////    @ResponseBody
////    int insertCnvCtd() throws IOException {
////        String path="C:\\Users\\Tai\\Documents\\专项CTD数据整理（航迹）\\35.2017南海综合航次第一航段\\hex\\0714-shifangqi.cnv";
////        String name="35.2017南海综合航次第一航段";
////        BufferedReader reader = new BufferedReader(new FileReader(path));
////        List<String> list = new ArrayList<>();
////        String tempString;
////        int flag=0;
////        while ((tempString = reader.readLine()) != null) {
////            if(flag==1){
////                list.add(tempString);
////            }
////            if(tempString.equals("*END*")){
////                flag=1;
////            }
////        }
////        reader.close();
////        for(int row=0;row<list.size();row++){
////            String temp=list.get(row).trim();
////            String[] datatemp=temp.split("\\s+");
////            //System.out.println(datatemp[0]);
////            CnvCtd cnvCtd=new CnvCtd();
////            cnvCtd.setName(name);
////            cnvCtd.setTimes(Double.parseDouble(datatemp[0]));
////            cnvCtd.setPumps(Integer.parseInt(datatemp[1]));
////            cnvCtd.setPrdm(Double.parseDouble(datatemp[2]));
////            cnvCtd.setDepsm(Double.parseDouble(datatemp[3]));
////            cnvCtd.setTemperature(Double.parseDouble(datatemp[4]));
////            cnvCtd.setDz(Double.parseDouble(datatemp[5]));
////            cnvCtd.setSbeox(Double.parseDouble(datatemp[6]));
////            cnvCtd.setConductivity(Double.parseDouble(datatemp[7]));
////            cnvCtd.setSvcm(Double.parseDouble(datatemp[8]));
////            cnvCtd.setDensity(Double.parseDouble(datatemp[9]));
////            cnvCtd.setSalinity(Double.parseDouble(datatemp[10]));
////            cnvCtd.setLatitude(Double.parseDouble(datatemp[11]));
////            cnvCtd.setLongitude(Double.parseDouble(datatemp[12]));
////            ctdService.insertCnvCtd(cnvCtd);
////            System.out.println(cnvCtd.getId());
////        }
////
////        return 1;
////    }

    //解析dbf文件获取站点数据
    @RequestMapping("/indbfsite")
    @ResponseBody
    int insertDbfSite() throws FileNotFoundException, DBFException {
        String dbfpath = env.getProperty("dbfpath");
        File dir = new File(dbfpath);
        File[] files = dir.listFiles();
        for (int ii = 0; ii < files.length; ii++) {
            if (files[ii].getName().endsWith("dbf")) {
                String path = dbfpath + "/" + files[ii].getName();
                System.out.println(files[ii].getName());
                InputStream fis = new FileInputStream(path);
                DBFReader reader = new DBFReader(fis);
                reader.setCharactersetName("UTF-8");
                // 调用DBFReader对实例方法得到path文件中字段的个数
                int fieldsCount = reader.getFieldCount();
                // 取出字段信息
                for (int i = 0; i < fieldsCount; i++) {
                    DBFField field = reader.getField(i);
                    System.out.print(field.getName() + " ");
                }
                System.out.println("");
                Object[] rowValues;
                // 一条条取出path文件中记录
                while ((rowValues = reader.nextRecord()) != null) {
                    Site site = new Site();
                    site.setName(files[ii].getName());

                    for (int i = 0; i < rowValues.length; i++) {
                        DBFField field = reader.getField(i);
                        DbfUtil.setSite(field.getName(), site, rowValues[i]);
                    }

                    if (site.getSite() != null && site.getSite().length() > 0) {
                        ctdService.insertSite(site);
                        System.out.println(site.getId());
                    }


                }
            }
        }
        return 1;
    }

    //解析dbf文件获取站点数据
    @RequestMapping("/indbfsite2")
    @ResponseBody
    int insertDbfSite2() throws FileNotFoundException, DBFException {
        String dbfpath = env.getProperty("dbfpath");
        File dir = new File(dbfpath);
        File[] files = dir.listFiles();
        for (int ii = 0; ii < files.length; ii++) {
            if (files[ii].getName().endsWith("dbf")) {
                String path = dbfpath + "/" + files[ii].getName();
                System.out.println(files[ii].getName());
                InputStream fis = new FileInputStream(path);
                DBFReader reader = new DBFReader(fis);
                reader.setCharactersetName("UTF-8");
                // 调用DBFReader对实例方法得到path文件中字段的个数
                int fieldsCount = reader.getFieldCount();
                // 取出字段信息
                for (int i = 0; i < fieldsCount; i++) {
                    DBFField field = reader.getField(i);
                    System.out.print(field.getName() + " ");
                }
                System.out.println("");
                Object[] rowValues;
                // 一条条取出path文件中记录
                while ((rowValues = reader.nextRecord()) != null) {
                    Site site = new Site();
                    site.setName(files[ii].getName());

                    for (int i = 0; i < rowValues.length; i++) {

                        DbfUtil.setSite2(i, site, rowValues[i]);
                    }

                    if (site.getSite() != null && site.getSite().length() > 0) {
                        ctdService.insertSite(site);
                        System.out.println(site.getId());
                    }


                }
            }
        }
        return 1;
    }

    @RequestMapping("/selsite")
    @ResponseBody
    String selSite(@RequestParam("name") String name, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<SiteInfo> siteInfoList = ctdService.selSite(name);
        Gson gson = new Gson();
        String json = gson.toJson(siteInfoList);
        return json;
    }

    @RequestMapping("/inctd")
    @ResponseBody
    int insertCtd() {
        String ctddirpath = env.getProperty("ctdPath");
        File dir = new File(ctddirpath);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith("json")) {
                String fullpath = ctddirpath + "/" + files[i].getName();
                System.out.println(fullpath);
                String jsonStr = FileUtil.ReadFile(fullpath);
                JSONArray jsonArray = JSON.parseArray(jsonStr);
                for (Object obj : jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;
                    Ctd ctd = new Ctd();
                    ctd.setLevel(files[i].getName());
                    ctd.setDepsm(jsonObject.getDouble("depsm"));
                    ctd.setPrdm(jsonObject.getDouble("prdm"));
                    ctd.setPotemp(jsonObject.getDouble("potemp"));
                    ctd.setSal00(jsonObject.getDouble("sal00"));
                    ctd.setSigma0(jsonObject.getDouble("sigma0"));
                    ctd.setSigmat00(jsonObject.getDouble("sigmat00"));
                    ctd.setSbeox(jsonObject.getDouble("sbeox"));
                    ctd.setDistance(jsonObject.getDouble("distance"));
                    ctd.setLongitude(jsonObject.getString("longitude"));
                    ctd.setLatitude(jsonObject.getString("latitude"));
                    System.out.println(ctd.getId());
                    ctdService.insertCtd(ctd);
                }

            }
        }
        return 1;
    }

    //根据层级查询ctd数据
    @RequestMapping("/selctd")
    @ResponseBody
    String selCtdData(@RequestParam("level") int level, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String temp = "depth" + level + ".json";
        List<CtdData> ctdDataList = ctdService.selCtdData(temp);
        Gson gson = new Gson();
        String json = gson.toJson(ctdDataList);
        return json;
    }

    //查询ctd侧栏数据
    @RequestMapping("/inctdlist")
    @ResponseBody
    int insertCtdList() {
        String ctdlistPath = env.getProperty("ctdlistPath");
        String jsonStr = FileUtil.ReadFile(ctdlistPath);
        CtdList ctdList = new CtdList();
        ctdList.setCtdListJson(jsonStr);
        ctdService.insertCtdList(ctdList);
        return ctdList.getId();
    }

    @RequestMapping("/selctdlist")
    @ResponseBody
    String selCtdList(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String json = ctdService.selCtdList();
        return json;
    }

    @RequestMapping("/inctdStation")
    @ResponseBody
    int insertCtdStation() {
        String stationPath = env.getProperty("stationPath");
        String stationPhotoPath = env.getProperty("stationPhotoPath");
        String jsonStr = FileUtil.ReadFile(stationPath);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        for (Object obj : jsonArray) {
            JSONObject jsonObjecte = (JSONObject) obj;
            String imageUrl = jsonObjecte.getString("imageUrl");
            String name = jsonObjecte.getString("name");
            String waterL = jsonObjecte.getString("waterL");
            String currentL = jsonObjecte.getString("currentL");
            String elementL = jsonObjecte.getString("elementL");
            String creatureL = jsonObjecte.getString("creatureL");
            int number = jsonObjecte.getInteger("id");
            Double lon = jsonObjecte.getDouble("lon");
            Double lat = jsonObjecte.getDouble("lat");
            PostgresTest postgresTest = new PostgresTest();
            if (postgresTest.uploadImage(stationPhotoPath, getPhotoName(imageUrl))) {
                Station station = new Station();
                station.setPhotoname(getPhotoName(imageUrl));
                station.setLon(lon);
                station.setLat(lat);
                station.setName(name);
                station.setNumber(number);
                station.setCurrentL(currentL);
                station.setWaterL(waterL);
                station.setElementL(elementL);
                station.setCreatureL(creatureL);
                ctdService.insertStation(station);
            }
        }


        return 1;
    }

    @RequestMapping("/inOBStation")
    @ResponseBody
    int inOBStation() throws FileNotFoundException {
        String stationPath = env.getProperty("stationPath");
        String serverip = env.getProperty("serverip");
        String jsonStr = FileUtil.ReadFile(stationPath);
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        for (Object obj : jsonArray) {
            JSONObject jsonObjecte = (JSONObject) obj;
            String imageUrl = jsonObjecte.getString("imageUrl");
            String name = jsonObjecte.getString("name");
            String waterL = jsonObjecte.getString("waterL");
            String currentL = jsonObjecte.getString("currentL");
            String elementL = jsonObjecte.getString("elementL");
            String creatureL = jsonObjecte.getString("creatureL");
            int number = jsonObjecte.getInteger("id");
            Double lon = jsonObjecte.getDouble("lon");
            Double lat = jsonObjecte.getDouble("lat");
            String imgurl = serverip + "/station/" + getPhotoName(imageUrl);
            PostgresTest postgresTest = new PostgresTest();
            OBStation obstation = new OBStation();
            obstation.setImgurl(imgurl);
            obstation.setLon(lon);
            obstation.setLat(lat);
            obstation.setName(name);
            obstation.setId(number);
            obstation.setCurrentL(currentL);
            obstation.setWaterL(waterL);
            obstation.setElementL(elementL);
            obstation.setCreatureL(creatureL);
            ctdService.insertOBStation(obstation);
        }


        return 1;
    }

    //查询ctd站点数据
    @RequestMapping("/selOBStation")
    @ResponseBody
    String selOBStation(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<OBStationData> obStationDataList = ctdService.selOBStation();
        Gson gson = new Gson();
        String json = gson.toJson(obStationDataList);
        return json;
    }

    @RequestMapping("/downimg")
    @ResponseBody
    int downimg() {
        PostgresTest postgresTest = new PostgresTest();
        String downPath = env.getProperty("downPath");
        postgresTest.downloadImage(downPath);
        return 1;
    }

    String stationName(String path) {
        String[] track = path.split("/");
        String filename = track[track.length - 1];
        String[] id = filename.split("[.]");
        return id[0];
    }

    String getPhotoName(String path) {
        String[] track = path.split("/");
        String filename = track[track.length - 1];

        return filename;
    }

    //解析cnv文件获取ctd数据
    @RequestMapping("/infullctd")
    @ResponseBody
    int insertFullCtd() throws IOException {
        String path = env.getProperty("ctdFullPath");
        String sailname = "科学三号201405黄海秋季调查航次";

        File dir = new File(path);
        File[] files = dir.listFiles();
        //解析每一个cnv文件
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith("cnv")) {
                String fullpath = path + "/" + files[i].getName();
                //获得站点名称
                String sitename = files[i].getName().substring(0, files[i].getName().length() - 11);
                BufferedReader reader = new BufferedReader(new FileReader(fullpath));
                List<String> list = new ArrayList<>();
                String tempString;
                int flag = 0;
                //读取有用的信息，*END*前的信息忽略
                while ((tempString = reader.readLine()) != null) {
                    if (flag == 1) {
                        list.add(tempString);
                    }
                    if (tempString.equals("*END*")) {
                        flag = 1;
                    }
                }
                reader.close();
                //每一列是一个对象
                for (int row = 0; row < list.size(); row++) {
                    String temp = list.get(row).trim();
                    String[] datatemp = temp.split("\\s+");
                    //输入放入对应的fullctd对象
                    FullCtd fullCtd = new FullCtd();
                    fullCtd.setSitename(sitename);
                    fullCtd.setSailname(sailname);
                    fullCtd.setPressure(Double.parseDouble(datatemp[0]));
                    fullCtd.setTemperature0(Double.parseDouble(datatemp[1]));
                    fullCtd.setConductivity0(Double.parseDouble(datatemp[2]));
                    fullCtd.setPump(Integer.parseInt(datatemp[3]));
                    fullCtd.setTemperature1(Double.parseDouble(datatemp[4]));
                    fullCtd.setConductivity1(Double.parseDouble(datatemp[5]));
                    fullCtd.setSalinity0(Double.parseDouble(datatemp[6]));
                    fullCtd.setSalinity1(Double.parseDouble(datatemp[7]));
                    fullCtd.setOxygen(Double.parseDouble(datatemp[8]));
                    fullCtd.setFluorescence(CtdUtil.getDoubleNumber(datatemp[9]));
                    fullCtd.setTurbidity(Double.parseDouble(datatemp[10]));
                    fullCtd.setPar(datatemp[11]);
                    fullCtd.setAcceleration(Double.parseDouble(datatemp[12]));
                    fullCtd.setAltimeter(Double.parseDouble(datatemp[13]));
                    fullCtd.setDescent(Double.parseDouble(datatemp[14]));
                    fullCtd.setDepth(Double.parseDouble(datatemp[15]));
                    fullCtd.setSound(Double.parseDouble(datatemp[16]));
                    fullCtd.setDensity0(Double.parseDouble(datatemp[17]));
                    fullCtd.setDensity1(Double.parseDouble(datatemp[18]));
                    fullCtd.setSalinity10(Double.parseDouble(datatemp[19]));
                    fullCtd.setSalinity11(Double.parseDouble(datatemp[20]));

                    //插入到数据库
                    ctdService.insertFullCtd(fullCtd);

                    System.out.print(fullCtd.getId());


                }
            }
        }

        return 1;
    }

}
