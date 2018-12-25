package com.iecas.oceanologybigdata.service;

import com.iecas.oceanologybigdata.model.QBAdcp;
import com.iecas.oceanologybigdata.model.QBAqd;
import com.iecas.oceanologybigdata.model.QBCtd;
import com.iecas.oceanologybigdata.model.QBInfo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

public interface IQBService {

    /**
     * 获取所有潜标坐标数据
     * @return
     */
    List<QBInfo> getQBInfos();

    /**
     * 根据潜标id获取潜标数据
     * @param id 潜标id
     * @return 返回潜标信息List
     */
    QBInfo getQBInfoByID(int id);

    /**
     * 根据潜标坐标获取潜标adq数据
     * @param LonLat 潜标坐标（格式：经度,维度）
     * @return 返回潜标信息List
     */
    List<QBAqd> getQBAqdsByLL(String LonLat);

    /**
     * 根据潜标坐标获取潜标ctd数据
     * @param LonLat 潜标坐标（格式：经度,维度）
     * @return 返回潜标ctd信息List
     */
    List<QBCtd> getQBCtdsByLL(String LonLat);

    /**
     * 根据潜标数据获取潜标adcp数据
     * @param LonLat 潜标坐标（格式：经度,维度）
     * @return 返回潜标adcp信息List
     */
    List<QBAdcp> getQBAdcpsByLL(String LonLat);
}
