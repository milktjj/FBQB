package com.iecas.oceanologybigdata.service.impl;

import com.iecas.oceanologybigdata.mapper.FBMapper;
import com.iecas.oceanologybigdata.model.*;
import com.iecas.oceanologybigdata.service.IFBService;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FBService implements IFBService {
    @Autowired
    FBMapper fbMapper;

    @Override
    public FBInfo getFBInfoById(String id) {
        return fbMapper.getFBInfoById(id);
    }

    @Override
    public List<FBInfo> getFBInfos() {
        return fbMapper.getFBInfos();
    }

    @Override
    public List<FBAdcp> getFBAdcpsById(String id) {
        return fbMapper.getFBAdcpsById(id);
    }

    @Override
    public List<FBAdcp> getFBAdcpsById(String id, String time) {
        return fbMapper.getFBAdcpsByIdAndTime(id, time);
    }

    @Override
    public List<FBBlog> getFBBlogsById(String id) {
        return fbMapper.getFBBlogsById(id);
    }

    @Override
    public List<FBBlog> getFBBlogsById(String id, String time) {
        return fbMapper.getFBBlogsByIdAndTime(id, time);
    }

    @Override
    public List<FBShzh> getFBShzhsById(String id) {
        return fbMapper.getFBShzhsById(id);
    }

    @Override
    public List<FBShzh> getFBShzhsById(String id, String time) {
        return fbMapper.getFBShzhsByIdAndTime(id, time);
    }

    @Override
    public List<FBQixg> getFBQixgById(String id) {
        return fbMapper.getFBQixgsById(id);
    }

    @Override
    public List<FBQixg> getFBQixgById(String id, String data) {
        return fbMapper.getFBQixgsByIdAndTime(id, data);
    }

    @Override
    public List<FBFeng> getFBFengsById(String id) {
        return fbMapper.getFBFengsById(id);
    }

    @Override
    public List<FBFeng> getFBFengsById(String id, String time) {
        return fbMapper.getFBFengsByIdAndTime(id, time);
    }


}
