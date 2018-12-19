package com.iecas.oceanologybigdata.service;

import com.iecas.oceanologybigdata.model.*;

import java.util.List;

public interface ITrackService {
    public int insertTrack(Track track);
    public int insertShipTrack(ShipTrack shipTrack);
    public int insertShip(Ship ship);
    public int insertTrackInfo(TrackInfo trackInfo);
    List<TrackData> selTrack();
    List<ShipTrackData> selShipTrack(String trackid);
    String selShip(String shipname);
    String selTrackInfo(String infoname);
    int insertSailTrack(SailTrack sailTrack);
    int insertSailTrackTime(SailTime sailTime);
}
