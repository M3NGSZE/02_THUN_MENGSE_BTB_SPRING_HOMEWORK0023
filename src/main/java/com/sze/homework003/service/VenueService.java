package com.sze.homework003.service;

import com.sze.homework003.model.dto.request.VenueRequest;
import com.sze.homework003.model.entity.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer size, Integer page);
    Venue getVenueById(Integer venueId);
    Venue addVenue(VenueRequest venueRequest);
    Venue updateVenueById(Integer venueId, VenueRequest venueRequest);
    Venue DeleteVenueById(Integer venueId);
}
