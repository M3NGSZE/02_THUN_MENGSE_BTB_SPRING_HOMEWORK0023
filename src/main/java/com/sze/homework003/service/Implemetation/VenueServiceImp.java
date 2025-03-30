package com.sze.homework003.service.Implemetation;

import com.sze.homework003.exception.NotFoundExceptionHandler;
import com.sze.homework003.model.dto.request.VenueRequest;
import com.sze.homework003.model.entity.Venue;
import com.sze.homework003.repository.VenueRepository;
import com.sze.homework003.service.VenueService;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

@Service
public class VenueServiceImp implements VenueService {
    private final VenueRepository venueRepository;
    public VenueServiceImp(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Integer size, Integer page) {
        if(venueRepository.getAllVenues(size, page) != null){
            return venueRepository.getAllVenues(size, page);
        }
        throw new NotFoundExceptionHandler("The venue id has not been founded.");
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        if (venueRepository.getVenueById(venueId) != null) {
            return venueRepository.getVenueById(venueId);
        }
        throw new NotFoundExceptionHandler("The venue id " + venueId + " has not been founded.");
    }

    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        return venueRepository.addVenue(venueRequest);
    }

    @Override
    public Venue updateVenueById(Integer venueId, VenueRequest venueRequest) {
        if(venueId <=0){
            throw new NotFoundExceptionHandler("The venue id must be greater than 0.");
        }
        if (venueRepository.getVenueById(venueId) != null) {
            return venueRepository.updateVenueById(venueId, venueRequest);
        }
        throw new NotFoundExceptionHandler("The venue id " + venueId + " has not been founded.");
    }

    @Override
    public Venue DeleteVenueById(Integer venueId) {
        if (venueRepository.getVenueById(venueId) != null) {
            return venueRepository.deleteVenueById(venueId);
        }
        throw new NotFoundExceptionHandler("The venue id " + venueId + " has not been founded.");
    }


}
