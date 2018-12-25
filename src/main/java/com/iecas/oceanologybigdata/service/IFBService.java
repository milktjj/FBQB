package com.iecas.oceanologybigdata.service;

import com.iecas.oceanologybigdata.model.*;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
public interface IFBService {
    /**
     * 根据浮标号获取浮标信息
     * @param id
     * @return
     */
    FBInfo getFBInfoById(String id);

    /**
     * 返回所有浮标信息
     * @return
     */
    List<FBInfo> getFBInfos();

    /**
     * 根据浮标号获取浮标数据
     * @param id
     * @return
     */
    List<FBAdcp> getFBAdcpsById(String id);
    List<FBAdcp> getFBAdcpsById(String id, String time);

    /**
     * 根据浮标号获取波浪信息
     * @param id
     * @return
     */
    List<FBBlog> getFBBlogsById(String id);
    List<FBBlog> getFBBlogsById(String id, String time);

    /**
     * 根据浮标号获取水质信息
     * @param id
     * @return
     */
    List<FBShzh> getFBShzhsById(String id);
    List<FBShzh> getFBShzhsById(String id, String time);

    /**
     * 根据浮标号获取气温气压
     * @param id
     * @return
     */
    List<FBQixg> getFBQixgById(String id);
    List<FBQixg> getFBQixgById(String id, String data);



    /**
     * 根据浮标号获取风速风向
     * @param id
     * @return
     */
    List<FBFeng> getFBFengsById(String id);
    List<FBFeng> getFBFengsById(String id, String time);

}
