package com.iecas.oceanologybigdata.service.impl;

import com.iecas.oceanologybigdata.mapper.EventMapper;
import com.iecas.oceanologybigdata.model.Event;
import com.iecas.oceanologybigdata.model.Tide;
import com.iecas.oceanologybigdata.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService{
    @Autowired
    private EventMapper eventMapper;
    @Override
    public int insertEvent(Event event) {
        return eventMapper.insertEvent(event);
    }

    @Override
    public int insertTide(Tide tide) {
        return eventMapper.insertTide(tide);
    }

    @Override
    public List<String> selectTide() {
        return eventMapper.selectTide();
    }
}
