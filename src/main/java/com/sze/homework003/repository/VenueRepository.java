package com.sze.homework003.repository;

import com.sze.homework003.model.dto.request.VenueRequest;
import com.sze.homework003.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
        SELECT * FROM venues
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    List<Venue> getAllVenues(Integer size, Integer page);

    @Select("""
        SELECT * FROM venues
        WHERE venue_id = #{venueId}
    """)
    @ResultMap("venueMapper")
    Venue getVenueById(Integer venueId);

    @Select("""
        INSERT INTO venues (venue_name, location)
        VALUES (#{venueName}, #{location})
        returning *
    """)
    @ResultMap("venueMapper")
    Venue addVenue(VenueRequest venueRequest);

    @Select("""
        UPDATE venues
        SET venue_name = #{venue.venueName}, location = #{venue.location}
        WHERE venue_id = #{venueId}
        returning *
    """)
    @ResultMap("venueMapper")
    Venue updateVenueById(Integer venueId, @Param("venue") VenueRequest venueRequest);

    @Select("""
        DELETE FROM venues
        WHERE venue_id = #{venueId}
        returning *
    """)
    @ResultMap("venueMapper")
    Venue deleteVenueById(Integer venueId);
}
