package com.sze.homework003.service.Implemetation;

import com.sze.homework003.exception.NotFoundExceptionHandler;
import com.sze.homework003.model.dto.request.AttendeeRequest;
import com.sze.homework003.model.entity.Attendee;
import com.sze.homework003.repository.AttendeeRepository;
import com.sze.homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    public AttendeeServiceImp(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }


    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        if(attendeeRepository.getAllAttendees(size, page) != null){
            return attendeeRepository.getAllAttendees(size, page);
        }
        throw new NotFoundExceptionHandler("The attendee id has not been founded.");
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        if (attendeeRepository.getAttendeeById(attendeeId) != null) {
            return attendeeRepository.getAttendeeById(attendeeId);
        }
        throw new NotFoundExceptionHandler("The attendee id " + attendeeId + " has not been founded.");
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.addVenue(attendeeRequest);
    }

    @Override
    public Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest) {
        if(attendeeId <=0){
            throw new NotFoundExceptionHandler("The attendee id must be greater than 0.");
        }
        if (attendeeRepository.getAttendeeById(attendeeId) != null) {
            return attendeeRepository.updateAttendeeById(attendeeId, attendeeRequest);
        }
        throw new NotFoundExceptionHandler("The attendee id " + attendeeId + " has not been founded.");
    }

    @Override
    public Attendee deleteAttendeeById(Integer attendeeId) {
        if (attendeeRepository.getAttendeeById(attendeeId) != null) {
            return attendeeRepository.deleteAttendeeId(attendeeId);
        }
        throw new NotFoundExceptionHandler("The attendee id " + attendeeId + " has not been founded.");
    }
}
