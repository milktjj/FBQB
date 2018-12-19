package com.iecas.oceanologybigdata.mapper;

import com.iecas.oceanologybigdata.model.Event;
import com.iecas.oceanologybigdata.model.SailTrack;
import com.iecas.oceanologybigdata.model.Tide;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//海洋事件
public interface EventMapper {
    //插入event数据
    @Insert("INSERT INTO public.tbl_event(value,label,eventstart,eventend)VALUES (#{value},#{label},#{eventstart},#{eventend})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertEvent(Event event);

    //插入tide数据
    @Insert("INSERT INTO public.tbl_tide(value, label, tidejson)VALUES (#{value},#{label},#{tidejson})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertTide(Tide tide);

    //查询tide的json数据
    @Select("SELECT tidejson FROM public.tbl_tide")
    public List<String> selectTide();
}
