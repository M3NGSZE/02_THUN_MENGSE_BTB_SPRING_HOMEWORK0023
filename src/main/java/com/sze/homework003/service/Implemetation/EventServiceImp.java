package com.sze.homework003.service.Implemetation;

import com.sze.homework003.exception.NotFoundExceptionHandler;
import com.sze.homework003.model.dto.request.EventRequest;
import com.sze.homework003.model.entity.Event;
import com.sze.homework003.repository.EventRepository;
import com.sze.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public List<Event> getAllEvents(Integer size, Integer page) {
        if(eventRepository.getAllEvents(size, page) != null){
            return eventRepository.getAllEvents(size, page);
        }
        throw new NotFoundExceptionHandler("The event id has not been founded.");
    }

    @Override
    public Event getEventById(Integer eventId) {
        if (eventRepository.getEventById(eventId) != null) {
            return eventRepository.getEventById(eventId);
        }
        throw new NotFoundExceptionHandler("The event " + eventId +" id has not been founded.");
    }

    @Override
    public Event addEvent(EventRequest eventRequest) {
        Integer eventId = eventRepository.addEvent(eventRequest);
        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            eventRepository.insertIntoEventAttendee(eventId, attendeeId);
        }

        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event updateEventById(Integer eventId, EventRequest eventRequest) {
        if (eventRepository.getEventById(eventId) != null) {
            eventRepository.deleteEventAttendeeById(eventId);

            for (Integer attendeeId : eventRequest.getAttendeeIds()) {
                eventRepository.insertIntoEventAttendee(eventId, attendeeId);
            }
            eventRepository.updateEventById(eventId, eventRequest);
            return eventRepository.getEventById(eventId);
        }
        throw new NotFoundExceptionHandler("The event id " + eventId + " has not been founded.");


    }

    @Override
    public Event deleteEventById(Integer eventId) {
        Event event = eventRepository.getEventById(eventId);

        if (event != null) {
            eventRepository.deleteEventById(eventId);
            return event;
        }
        throw new NotFoundExceptionHandler("The event id " + eventId + " has not been found.");
    }
}
