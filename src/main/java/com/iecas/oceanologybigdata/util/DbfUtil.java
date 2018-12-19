package com.iecas.oceanologybigdata.util;

import com.iecas.oceanologybigdata.model.Site;

public class DbfUtil {
    public static void setSite(String temp, Site site,Object object){
        if(temp.equals("站位号")||temp.equals("站位")||temp.equals("站号")){
            String sitename;
            if(object instanceof Double){
                sitename=""+(double)object;
            }else {
                sitename=(String) object;
            }

            site.setSite(sitename.trim());
        }else if(temp.equals("取样方")){
            String style=(String)object;
            site.setStyle(style.trim());
        }else if(temp.equals("经度")){
            double lon=(double)object;
            site.setLon(lon);
        }else if(temp.equals("纬度")){
            double lat=(double)object;
            site.setLat(lat);
        }else if(temp.equals("水深")){
            double depth;
            if(object instanceof String){
                String s=(String)object;
                if(s==null ||s.trim().length()<=0){
                    depth=0.000;
                }else {
                    depth=Double.parseDouble((String) object);
                }
            }else{
                depth=(double)object;
            }
            site.setDepth(depth);
        }else if(temp.equals("采样日")||temp.equals("日期")){
            String date;
            if(object instanceof String){
                date=(String)object;
            }else {
                date="";
            }

            site.setDate(date.trim());
        }else if(temp.equals("样品类")){
            String type=(String)object;
            site.setType(type.trim());
        }
    }
    public static void setSite2(int temp, Site site,Object object){
        if(temp==0){
            String sitename;
            if(object instanceof Double){
                sitename=""+(double)object;
            }else {
                sitename=(String) object;
            }

            site.setSite(sitename.trim());
        }else if(temp==1){
            double lon=(double)object;
            site.setLon(lon);
        }else if(temp==2){
            double lat=(double)object;
            site.setLat(lat);
        }else if(temp==3){
            double depth=(double)object;
            site.setDepth(depth);
        }else if(temp==4){
            String date;
            if(object instanceof String){
                date=(String)object;
            }else {
                date="";
            }

            site.setDate(date.trim());
        }
    }
}
