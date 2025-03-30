package com.sze.homework003.repository;

import com.sze.homework003.model.dto.request.AttendeeRequest;
import com.sze.homework003.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
        SELECT * FROM attendee
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    List<Attendee> getAllAttendees(Integer size, Integer page);

    @Select("""
        SELECT * FROM attendee
        WHERE attendee_id = #{attendeeId}
    """)
    @ResultMap("attendeeMapper")
    Attendee getAttendeeById(Integer attendeeId);

    @Select("""
        INSERT INTO attendee (attendee_name, email)
        VALUES (#{attendeeName}, #{email})
        returning *
    """)
    @ResultMap("attendeeMapper")
    Attendee addVenue(AttendeeRequest attendeeRequest);

    @Select("""
        UPDATE attendee
        SET attendee_name = #{attendee.attendeeName}, email = #{attendee.email}
        WHERE attendee_id = #{attendeeId}
        returning *
    """)
    @ResultMap("attendeeMapper")
    Attendee updateAttendeeById(Integer attendeeId, @Param("attendee") AttendeeRequest attendeeRequest);

    @Select("""
        DELETE FROM attendee
        WHERE attendee_id = #{attendeeId}
        returning *
    """)
    @ResultMap("attendeeMapper")
    Attendee deleteAttendeeId(Integer attendeeId);

    @Select("""
        SELECT a.* FROM attendee a 
         INNER JOIN event_attendee ea 
         ON  a.attendee_id = ea.attendee_id 
         WHERE ea.event_id = #{eventId};
    """)
    @ResultMap("attendeeMapper")
    List<Attendee> getAllAttendeesByEventId(Integer eventId);
}
