package com.iecas.oceanologybigdata.mapper;

import com.iecas.oceanologybigdata.model.*;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FBMapper {
    @Select("SELECT \"fbId\", lon, lat, description, depth,icon FROM public.tbl_fbinfo WHERE \"fbId\"=#{0}")
    @Results({
            @Result(column = "fbId", property = "fbId"),
            @Result(column = "lon", property = "lon"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "depth", property = "depth"),
            @Result(column = "icon", property = "icon")
    })
    FBInfo getFBInfoById(String id);


    @Select("SELECT \"fbId\", lon, lat, description, depth,icon FROM public.tbl_fbinfo")
    @Results({
            @Result(column = "fbId", property = "fbId"),
            @Result(column = "lon", property = "lon"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "depth", property = "depth"),
            @Result(column = "icon", property = "icon")
    })
    List<FBInfo> getFBInfos();


    @Select("SELECT \"流向1\",\"流速1\",\"日期时间\" from tbl_fbadcp WHERE \"浮标号\" LIKE '${0}%'")
    @Results({
            @Result(column = "流向1", property = "flowDir1"),
            @Result(column = "流速1", property = "flowRate1"),
            @Result(column = "日期时间", property = "time")
    })
    List<FBAdcp> getFBAdcpsById(String id);

    @Select("SELECT \"流向1\",\"流速1\",\"日期时间\" from tbl_fbadcp WHERE \"浮标号\" LIKE '${id}%' AND \"日期时间\" LIKE concat(#{date},'%')")
    @Results({
            @Result(column = "流向1", property = "flowDir1"),
            @Result(column = "流速1", property = "flowRate1"),
            @Result(column = "日期时间", property = "time")
    })
    List<FBAdcp> getFBAdcpsByIdAndTime(String id, String date);

    @Select("SELECT \"日期时间\",\"有效波高\",\"有效波周期\",\"平均波高\",\"平均波周期\",\"最大波高\",\"最大波周期\" from tbl_fbblog WHERE \"浮标号\" LIKE '${0}%'")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "有效波高", property = "validWaveHeight"),
            @Result(column = "有效波周期", property = "validWaveCycle"),
            @Result(column = "最大波高", property = "maxWaveHeight"),
            @Result(column = "最大波周期", property = "maxWaveCycle"),
            @Result(column = "平均波高", property = "avgWaveHeight"),
            @Result(column = "平均波周期", property = "avgWaveCycle")
    })
    List<FBBlog> getFBBlogsById(String id);

    @Select("SELECT \"日期时间\",\"有效波高\",\"有效波周期\",\"平均波高\",\"平均波周期\",\"最大波高\",\"最大波周期\" from tbl_fbblog WHERE \"浮标号\" LIKE '${0}%'  AND \"日期时间\" LIKE concat(#{date},'%')")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "有效波高", property = "validWaveHeight"),
            @Result(column = "有效波周期", property = "validWaveCycle"),
            @Result(column = "最大波高", property = "maxWaveHeight"),
            @Result(column = "最大波周期", property = "maxWaveCycle"),
            @Result(column = "平均波高", property = "avgWaveHeight"),
            @Result(column = "平均波周期", property = "avgWaveCycle")
    })
    List<FBBlog> getFBBlogsByIdAndTime(String id, String date);

    @Select("SELECT \"日期时间\",\"浊度\", \"叶绿素\",\"水温\",\"盐度\" FROM public.tbl_fbshzh WHERE \"浮标号\" LIKE '${0}%'")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "浊度", property = "turbidity"),
            @Result(column = "叶绿素", property = "chlorophyl"),
            @Result(column = "水温", property = "waterTemp"),
            @Result(column = "盐度", property = "salinity")
    })
    List<FBShzh> getFBShzhsById(String id);

    @Select("SELECT \"日期时间\",\"浊度\", \"叶绿素\",\"水温\",\"盐度\" FROM public.tbl_fbshzh WHERE \"浮标号\" LIKE '${0}%' AND \"日期时间\" LIKE concat(#{date},'%')")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "浊度", property = "turbidity"),
            @Result(column = "叶绿素", property = "chlorophyl"),
            @Result(column = "水温", property = "waterTemp"),
            @Result(column = "盐度", property = "salinity")
    })
    List<FBShzh> getFBShzhsByIdAndTime(String id, String date);

    @Select("SELECT \"日期时间\",\"气温\",\"气压\" FROM public.tbl_fbqixg WHERE \"浮标号\" LIKE '${0}%' AND \"日期时间\"")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "气温", property = "temperature"),
            @Result(column = "气压", property = "pressure")
    })
    List<FBQixg> getFBQixgsById(String id);

    @Select("SELECT \"日期时间\",\"气温\",\"气压\" FROM public.tbl_fbqixg WHERE \"浮标号\" LIKE '${id}%' AND \"日期时间\" LIKE concat(#{date},'%')")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "气温", property = "temperature"),
            @Result(column = "气压", property = "pressure")
    })
    List<FBQixg> getFBQixgsByIdAndTime(String id, String date);

    @Select("SELECT \"日期时间\",\"十分钟平均风速\",\"十分钟平均风向\" FROM public.tbl_fbfeng WHERE  \"浮标号\" LIKE '${0}%'")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "十分钟平均风速", property = "avgWindSpeed_10M"),
            @Result(column = "十分钟平均风向", property = "avgWindDir_10M")
    })
    List<FBFeng> getFBFengsById(String id);

    @Select("SELECT \"日期时间\",\"十分钟平均风速\",\"十分钟平均风向\" FROM public.tbl_fbfeng WHERE  \"浮标号\" LIKE '${0}%' AND \"日期时间\" LIKE concat(#{date},'%')")
    @Results({
            @Result(column = "日期时间", property = "time"),
            @Result(column = "十分钟平均风速", property = "avgWindSpeed_10M"),
            @Result(column = "十分钟平均风向", property = "avgWindDir_10M")
    })
    List<FBFeng> getFBFengsByIdAndTime(String id,String date);
}
