package com.sze.homework003.service;

import com.sze.homework003.model.dto.request.EventRequest;
import com.sze.homework003.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer size, Integer page);
    Event getEventById(Integer eventId);
    Event addEvent(EventRequest eventRequest);
    Event updateEventById(Integer eventId, EventRequest eventRequest);
    Event deleteEventById(Integer eventId);

}
