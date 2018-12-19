package com.iecas.oceanologybigdata.mapper;

import com.iecas.oceanologybigdata.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TrackMapper {
    @Insert("INSERT INTO public.tbl_track(id, name, \"startTime\", \"endTime\")VALUES (#{id},#{name},#{startTime},#{endTime})")
    @Options(useGeneratedKeys = true,keyProperty = "no",keyColumn = "no")
    int insertTrack(Track track);
    @Insert("INSERT INTO public.tbl_shiptrack(\n" +
            "\ttrackid, \"codeNum\", datetime, lat, lng, \"metadataId\", station, \"waterDepth\", vsid)\n" +
            "\tVALUES (#{trackid},#{codeNum},#{datetime},#{lat},#{lng},#{metadataId},#{station},#{waterDepth},#{vsid});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertShipTrack(ShipTrack shipTrack);
    @Insert("INSERT INTO public.tbl_ship(\"shipJson\",shipname)VALUES (#{shipJson},#{shipname})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertShip(Ship ship);
    @Insert("INSERT INTO public.tbl_trackinfo(info,infoname)VALUES (#{info},#{infoname});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertTrackInfo(TrackInfo trackInfo);
    //查询track数据
    @Select("SELECT id, name, \"startTime\", \"endTime\" FROM public.tbl_track")
    List<TrackData> selTrack();
    //根据船id查询航迹数据
    @Select("SELECT \"codeNum\", datetime, lat, lng, \"metadataId\", station, \"waterDepth\", vsid FROM public.tbl_shiptrack WHERE trackid=#{trackid}")
    List<ShipTrackData> selShipTrack(String trackid);
    //根据ship名字查询ship数据
    @Select("SELECT \"shipJson\" FROM public.tbl_ship where shipname=#{shipname}")
    String selShip(String shipname);
    //根据infoname查询trackinfo数据
    @Select("SELECT info FROM public.tbl_trackinfo where infoname=#{infoname}")
    String selTrackInfo(String infoname);
    //插入sailtrack数据
    @Insert("INSERT INTO public.tbl_sailtrack(sailname, lon, lat, datetime)" +
            "VALUES (#{sailname},#{lon},#{lat},#{datetime});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertSailTrack(SailTrack sailTrack);

    //插入sailtracktime数据
    @Insert("INSERT INTO public.tbl_sailtime(sailname, starttime, endtime)" +
            "VALUES (#{sailname},#{starttime},#{endtime});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertSailTrackTime(SailTime sailTime);
    @Select("")
    List<SailTrack> selSailTrack(String sailname);
}
