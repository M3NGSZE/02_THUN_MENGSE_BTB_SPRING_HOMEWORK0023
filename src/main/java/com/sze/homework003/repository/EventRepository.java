package com.sze.homework003.repository;

import com.sze.homework003.model.dto.request.EventRequest;
import com.sze.homework003.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    @Select("""
        SELECT * FROM events ORDER BY event_id OFFSET #{size} * (#{page} - 1) LIMIT #{size}
    """)
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.sze.homework003.repository.VenueRepository.getVenueById")
            ),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "com.sze.homework003.repository.AttendeeRepository.getAllAttendeesByEventId")
            )

    })
    List<Event> getAllEvents(Integer size, Integer page);

    @Select("""
        SELECT * FROM events
        WHERE event_id = #{eventId}
    """)
    @ResultMap("eventMapper")
    Event getEventById(Integer eventId);

    @Select("""
        INSERT INTO events (event_name, event_date, venue_id)
        VALUES (#{eventName}, #{eventDate}, #{venueId})
        returning event_id;
    """)
    Integer addEvent(EventRequest eventRequest);

    @Insert("""
        INSERT INTO event_attendee VALUES (default,#{eventId},#{attendeeId}) 
    """)
    void insertIntoEventAttendee(Integer eventId, Integer attendeeId);

    @Select("""
        UPDATE events
        SET event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId}
        WHERE event_id = #{eventId}
        returning *
    """)
    @ResultMap("eventMapper")
    Event updateEventById(Integer eventId,@Param("event") EventRequest eventRequest);

    @Select("""
        DELETE FROM event_attendee WHERE event_id = #{id}
    """)
    @ResultMap("eventMapper")
    Event deleteEventAttendeeById(Integer id);

    @Select("""
        DELETE FROM events 
        where event_id = #{id}
        returning *
    """)
    @ResultMap("eventMapper")
    Event deleteEventById(Integer id);
}
