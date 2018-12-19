package com.iecas.oceanologybigdata.service.impl;

import com.iecas.oceanologybigdata.mapper.TrackMapper;
import com.iecas.oceanologybigdata.model.*;
import com.iecas.oceanologybigdata.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService implements ITrackService {
    @Autowired
    private TrackMapper trackMapper;
    @Override
    public int insertTrack(Track track) {
        return trackMapper.insertTrack(track);
    }

    @Override
    public int insertShipTrack(ShipTrack shipTrack) {
        return trackMapper.insertShipTrack(shipTrack);
    }

    @Override
    public int insertShip(Ship ship) {
        return trackMapper.insertShip(ship);
    }

    @Override
    public int insertTrackInfo(TrackInfo trackInfo) {
        return trackMapper.insertTrackInfo(trackInfo);
    }

    @Override
    public List<TrackData> selTrack() {
        return trackMapper.selTrack();
    }

    @Override
    public List<ShipTrackData> selShipTrack(String trackid) {
        return trackMapper.selShipTrack(trackid);
    }

    @Override
    public String selShip(String shipname) {
        return trackMapper.selShip(shipname);
    }

    @Override
    public String selTrackInfo(String infoname) {
        return trackMapper.selTrackInfo(infoname);
    }

    @Override
    public int insertSailTrack(SailTrack sailTrack) {
        return trackMapper.insertSailTrack(sailTrack);
    }

    @Override
    public int insertSailTrackTime(SailTime sailTime) {
        return trackMapper.insertSailTrackTime(sailTime);
    }
}
