package com.iecas.oceanologybigdata.service.impl;

import com.iecas.oceanologybigdata.mapper.CtdMapper;
import com.iecas.oceanologybigdata.mapper.EventMapper;
import com.iecas.oceanologybigdata.model.*;
import com.iecas.oceanologybigdata.service.ICtdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CtdService implements ICtdService{
    @Autowired
    private CtdMapper ctdMapper;
    @Override
    public int insertCtd(Ctd ctd) {
        return ctdMapper.insertCtd(ctd);
    }

    @Override
    public int insertCnvCtd(CnvCtd cnvCtd) {
        return ctdMapper.insertCnvCtd(cnvCtd);
    }

    @Override
    public int insertCtdList(CtdList ctdList) {
        return ctdMapper.insertCtdList(ctdList);
    }

    @Override
    public int insertStation(Station station) {
        return ctdMapper.insertStation(station);
    }

    @Override
    public List<CtdData> selCtdData(String level) {
        return ctdMapper.selCtdData(level);
    }

    @Override
    public String selCtdList() {
        return ctdMapper.selCtdList();
    }

    @Override
    public int insertOBStation(OBStation obStation) {
        return ctdMapper.insertOBStation(obStation);
    }

    @Override
    public List<OBStationData> selOBStation() {
        return ctdMapper.selOBStation();
    }

    @Override
    public int insertSite(Site site) {
        return ctdMapper.insertSite(site);
    }

    @Override
    public List<SiteInfo> selSite(String name) {
        return ctdMapper.selSite(name);
    }

    @Override
    public int insertFullCtd(FullCtd fullCtd) {
        return ctdMapper.insertFullCtd(fullCtd);
    }

}
