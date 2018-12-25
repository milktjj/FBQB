package com.iecas.oceanologybigdata.service.impl;

import com.iecas.oceanologybigdata.mapper.QBMapper;
import com.iecas.oceanologybigdata.model.QBAdcp;
import com.iecas.oceanologybigdata.model.QBAqd;
import com.iecas.oceanologybigdata.model.QBCtd;
import com.iecas.oceanologybigdata.model.QBInfo;
import com.iecas.oceanologybigdata.service.IQBService;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QBService implements IQBService {
    @Autowired
    QBMapper qbMapper;

    @Override
    public List<QBInfo> getQBInfos() { return qbMapper.getQBInfos();}

    @Override
    public QBInfo getQBInfoByID(int id) { return qbMapper.getQBInfosByID(id);}

    @Override
    public List<QBAqd> getQBAqdsByLL(String LonLat) {return qbMapper.getQBAqdsByLL(LonLat);}

    @Override
    public List<QBCtd> getQBCtdsByLL(String LonLat) {return qbMapper.getQBCtdsByLL(LonLat);}

    @Override
    public List<QBAdcp> getQBAdcpsByLL(String LonLat) { return qbMapper.getQBAdcpsByLL(LonLat);}


}
