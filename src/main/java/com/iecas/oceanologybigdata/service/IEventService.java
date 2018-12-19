package com.iecas.oceanologybigdata.service;

import com.iecas.oceanologybigdata.model.Event;
import com.iecas.oceanologybigdata.model.Tide;

import java.util.List;

public interface IEventService {
    public int insertEvent(Event event);
    public int insertTide(Tide tide);
    public List<String> selectTide();
}
