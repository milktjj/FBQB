package com.iecas.oceanologybigdata.service;

import com.iecas.oceanologybigdata.model.*;

import java.util.List;

public interface ICtdService {
    int insertCtd(Ctd ctd);
    int insertCnvCtd(CnvCtd cnvCtd);
    int insertCtdList(CtdList ctdList);
    int insertStation(Station station);
    List<CtdData> selCtdData(String level);
    String selCtdList();
    int insertOBStation(OBStation obStation);
    List<OBStationData> selOBStation();
    int insertSite(Site site);
    List<SiteInfo> selSite(String name);
    int insertFullCtd(FullCtd fullCtd);

}
