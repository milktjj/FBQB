package com.iecas.oceanologybigdata.mapper;

import com.iecas.oceanologybigdata.model.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CtdMapper {
    @Insert("INSERT INTO public.tbl_cnvctd(name,times, pumps, prdm, depsm, temperature, dz, sbeox, conductivity, svcm,density,salinity,longitude, latitude)VALUES (#{name},#{times},#{pumps},#{prdm},#{depsm},#{temperature},#{dz},#{sbeox},#{conductivity},#{svcm},#{density},#{salinity},#{longitude},#{latitude})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    //根据cnv类型的文件解析插入ctd数据
    int insertCnvCtd(CnvCtd cnvCtd);
    @Insert("INSERT INTO public.tbl_ctd(level, depsm, prdm, potemp, sal00, sigma0, sigmat00, sbeox, distance, longitude, latitude, areaid)" +
            "VALUES (#{level},#{depsm},#{prdm},#{potemp},#{sal00},#{sigma0},#{sigmat00},#{sbeox},#{distance},#{longitude},#{latitude},#{areaid})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertCtd(Ctd ctd);
    @Insert("INSERT INTO public.tbl_ctdlist(\"ctdListJson\")VALUES (#{ctdListJson})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertCtdList(CtdList ctdList);
    @Insert("INSERT INTO public.tbl_stationinfo(photoname, lon, lat, name, \"number\", \"waterL\", \"currentL\", \"elementL\", \"creatureL\")VALUES (#{photoname},#{lon},#{lat},#{name},#{number},#{waterL},#{currentL},#{elementL},#{creatureL})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertStation(Station station);
    @Insert("INSERT INTO public.tbl_obstation(imgurl, lon, lat, name, \"id\", \"waterL\", \"currentL\", \"elementL\", \"creatureL\")VALUES (#{imgurl},#{lon},#{lat},#{name},#{id},#{waterL},#{currentL},#{elementL},#{creatureL})")
    @Options(useGeneratedKeys = true,keyProperty = "no",keyColumn = "no")
    int insertOBStation(OBStation obStation);
    //查询ctd数据
    @Select("SELECT depsm, prdm, potemp, sal00, sigma0, sigmat00, sbeox, distance, longitude, latitude FROM public.tbl_ctd where level=#{level}")
    List<CtdData> selCtdData(String level);
    //查询ctd侧栏数据
    @Select("SELECT \"ctdListJson\" FROM public.tbl_ctdlist limit 1")
    String selCtdList();
    //查询ctd站点数据
    @Select("SELECT imgurl, lon, lat, name, \"waterL\", \"currentL\", \"elementL\", \"creatureL\", id FROM public.tbl_obstation")
    List<OBStationData> selOBStation();
    //根据dbf类型的文件插入site数据
    @Insert("INSERT INTO public.tbl_site(name,site,style,lon,lat,depth,date,type)VALUES (#{name},#{site},#{style},#{lon},#{lat},#{depth},#{date},#{type})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertSite(Site site);
    //查询site数据
    @Select("SELECT site,lon,lat FROM public.tbl_site where name=#{name}")
    List<SiteInfo> selSite(String name);
    //插入fullctd数据
    @Insert("INSERT INTO public.tbl_fullctd(" +
            "sailname, sitename, pressure, temperature0, conductivity0, pump, temperature1, conductivity1, salinity0, salinity1, oxygen, fluorescence, turbidity, acceleration, altimeter, descent, depth, sound, density0, density1, salinity10, salinity11, par)" +
            "VALUES (#{sailname},#{sitename},#{pressure},#{temperature0},#{conductivity0},#{pump},#{temperature1},#{conductivity1},#{salinity0},#{salinity1},#{oxygen},#{fluorescence},#{turbidity},#{acceleration},#{altimeter},#{descent},#{depth},#{sound},#{density0},#{density1},#{salinity10},#{salinity11},#{par});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertFullCtd(FullCtd fullCtd);
}
