package com.iecas.oceanologybigdata.mapper;

import com.iecas.oceanologybigdata.model.QBAdcp;
import com.iecas.oceanologybigdata.model.QBAqd;
import com.iecas.oceanologybigdata.model.QBCtd;
import com.iecas.oceanologybigdata.model.QBInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QBMapper {
    @Select("SELECT id, lon, lat,depth FROM public.tbl_qbinfo")
    @Results({
            @Result(column = "id", property = "qbId"),
            @Result(column = "lon", property = "lon"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "depth", property = "depth")
    }
    )
    List<QBInfo> getQBInfos();


    @Select("SELECT id, lon, lat,depth FROM public.tbl_qbinfo WHERE id=#{0}")
    @Results({
            @Result(column = "id", property = "qbId"),
            @Result(column = "lon", property = "lon"),
            @Result(column = "lat", property = "lat"),
            @Result(column = "depth", property = "depth")
        }
    )
    QBInfo getQBInfosByID(int id);

    @Select("SElECT dstime, dspressure, cuspeed, cvspeed, cwspeed FROM public.tbl_qbaqd WHERE lonlat=#{0} AND dstime > '2018-11-03'")
    @Results({
            @Result(column = "dstime", property = "timestamp"),
            @Result(column = "dspressure", property = "pressure"),
            @Result(column = "cuspeed", property = "cuSpeed"),
            @Result(column = "cvspeed", property = "cvSpeed"),
            @Result(column = "cwspeed", property = "cwSpeed"),
    })
    List<QBAqd> getQBAqdsByLL(String LonLat);

    @Select("SELECT dstime, dstemp, dspressure, dssality from public.tbl_qbctd WHERE lonlat=#{0} AND dstime > '2018-11-03'")
    @Results({
            @Result(column = "dstime", property = "timestamp"),
            @Result(column = "dstemp", property = "temperature"),
            @Result(column = "dspressure", property = "pressure"),
            @Result(column = "dssality", property = "sality")
    })
    List<QBCtd> getQBCtdsByLL(String LonLat);

    @Select("SELECT * FROM public.tbl_qbadcp WhERE lonlat=#{0} AND dstime > '2018-11-03'")
    @Results({
            @Result(column = "dstime", property = "timestamp"),
            @Result(column = "updown", property = "updown"),
            @Result(column = "depth", property = "depth"),
            @Result(column = "cu1",property = "cu1"),
            @Result(column = "cv1",property = "cv1"),
            @Result(column = "cu2",property = "cu2"),
            @Result(column = "cv2",property = "cv2"),
            @Result(column = "cu3",property = "cu3"),
            @Result(column = "cv3",property = "cv3"),
            @Result(column = "cu4",property = "cu4"),
            @Result(column = "cv4",property = "cv4"),
            @Result(column = "cu5",property = "cu5"),
            @Result(column = "cv5",property = "cv5"),
            @Result(column = "cu6",property = "cu6"),
            @Result(column = "cv6",property = "cv6"),
            @Result(column = "cu7",property = "cu7"),
            @Result(column = "cv7",property = "cv7"),
            @Result(column = "cu8",property = "cu8"),
            @Result(column = "cv8",property = "cv8"),
            @Result(column = "cu9",property = "cu9"),
            @Result(column = "cv9",property = "cv9"),
            @Result(column = "cu10",property = "cu10"),
            @Result(column = "cv10",property = "cv10"),
            @Result(column = "cu11",property = "cu11"),
            @Result(column = "cv11",property = "cv11"),
            @Result(column = "cu12",property = "cu12"),
            @Result(column = "cv12",property = "cv12"),
            @Result(column = "cu13",property = "cu13"),
            @Result(column = "cv13",property = "cv13"),
            @Result(column = "cu14",property = "cu14"),
            @Result(column = "cv14",property = "cv14"),
            @Result(column = "cu15",property = "cu15"),
            @Result(column = "cv15",property = "cv15"),
            @Result(column = "cu16",property = "cu16"),
            @Result(column = "cv16",property = "cv16"),
            @Result(column = "cu17",property = "cu17"),
            @Result(column = "cv17",property = "cv17"),
            @Result(column = "cu18",property = "cu18"),
            @Result(column = "cv18",property = "cv18"),
            @Result(column = "cu19",property = "cu19"),
            @Result(column = "cv19",property = "cv19"),
            @Result(column = "cu20",property = "cu20"),
            @Result(column = "cv20",property = "cv20"),
            @Result(column = "cu21",property = "cu21"),
            @Result(column = "cv21",property = "cv21"),
            @Result(column = "cu22",property = "cu22"),
            @Result(column = "cv22",property = "cv22"),
            @Result(column = "cu23",property = "cu23"),
            @Result(column = "cv23",property = "cv23"),
            @Result(column = "cu24",property = "cu24"),
            @Result(column = "cv24",property = "cv24"),
            @Result(column = "cu25",property = "cu25"),
            @Result(column = "cv25",property = "cv25"),
            @Result(column = "cu26",property = "cu26"),
            @Result(column = "cv26",property = "cv26"),
            @Result(column = "cu27",property = "cu27"),
            @Result(column = "cv27",property = "cv27"),
            @Result(column = "cu28",property = "cu28"),
            @Result(column = "cv28",property = "cv28"),
            @Result(column = "cu29",property = "cu29"),
            @Result(column = "cv29",property = "cv29"),
            @Result(column = "cu30",property = "cu30"),
            @Result(column = "cv30",property = "cv30"),
            @Result(column = "cu31",property = "cu31"),
            @Result(column = "cv31",property = "cv31"),
            @Result(column = "cu32",property = "cu32"),
            @Result(column = "cv32",property = "cv32"),
            @Result(column = "cu33",property = "cu33"),
            @Result(column = "cv33",property = "cv33"),
            @Result(column = "cu34",property = "cu34"),
            @Result(column = "cv34",property = "cv34"),
            @Result(column = "cu35",property = "cu35"),
            @Result(column = "cv35",property = "cv35"),
            @Result(column = "cu36",property = "cu36"),
            @Result(column = "cv36",property = "cv36"),
            @Result(column = "cu37",property = "cu37"),
            @Result(column = "cv37",property = "cv37"),
            @Result(column = "cu38",property = "cu38"),
            @Result(column = "cv38",property = "cv38"),
            @Result(column = "cu39",property = "cu39"),
            @Result(column = "cv39",property = "cv39"),
            @Result(column = "cu40",property = "cu40"),
            @Result(column = "cv40",property = "cv40"),
            @Result(column = "cu41",property = "cu41"),
            @Result(column = "cv41",property = "cv41"),
            @Result(column = "cu42",property = "cu42"),
            @Result(column = "cv42",property = "cv42"),
            @Result(column = "cu43",property = "cu43"),
            @Result(column = "cv43",property = "cv43"),
            @Result(column = "cu44",property = "cu44"),
            @Result(column = "cv44",property = "cv44"),
            @Result(column = "cu45",property = "cu45"),
            @Result(column = "cv45",property = "cv45"),
            @Result(column = "cu46",property = "cu46"),
            @Result(column = "cv46",property = "cv46"),
            @Result(column = "cu47",property = "cu47"),
            @Result(column = "cv47",property = "cv47"),
            @Result(column = "cu48",property = "cu48"),
            @Result(column = "cv48",property = "cv48"),
            @Result(column = "cu49",property = "cu49"),
            @Result(column = "cv49",property = "cv49"),
            @Result(column = "cu50",property = "cu50"),
            @Result(column = "cv50",property = "cv50"),
            @Result(column = "cu51",property = "cu51"),
            @Result(column = "cv51",property = "cv51"),
            @Result(column = "cu52",property = "cu52"),
            @Result(column = "cv52",property = "cv52"),
            @Result(column = "cu53",property = "cu53"),
            @Result(column = "cv53",property = "cv53"),
            @Result(column = "cu54",property = "cu54"),
            @Result(column = "cv54",property = "cv54"),
            @Result(column = "cu55",property = "cu55"),
            @Result(column = "cv55",property = "cv55"),
            @Result(column = "cu56",property = "cu56"),
            @Result(column = "cv56",property = "cv56"),
            @Result(column = "cu57",property = "cu57"),
            @Result(column = "cv57",property = "cv57"),
            @Result(column = "cu58",property = "cu58"),
            @Result(column = "cv58",property = "cv58"),
            @Result(column = "cu59",property = "cu59"),
            @Result(column = "cv59",property = "cv59"),
            @Result(column = "cu60",property = "cu60"),
            @Result(column = "cv60",property = "cv60")
    })
    List<QBAdcp> getQBAdcpsByLL(String LonLat);


}
