package com.sze.homework003.service;

import com.sze.homework003.model.dto.request.AttendeeRequest;
import com.sze.homework003.model.entity.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer page, Integer size);
    Attendee getAttendeeById(Integer attendeeId);
    Attendee addAttendee(AttendeeRequest attendeeRequest);
    Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest);
    Attendee deleteAttendeeById(Integer attendeeId);
}
