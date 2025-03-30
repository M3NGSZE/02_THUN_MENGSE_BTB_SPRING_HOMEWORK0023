package com.sze.homework003.controller;

import com.sze.homework003.model.dto.request.VenueRequest;
import com.sze.homework003.model.entity.Venue;
import com.sze.homework003.model.dto.request.response.ApiResponse;
import com.sze.homework003.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "venue-controller")
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    @Operation(summary = "Get all venues")
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "must greater than 0") Integer page,
            @RequestParam(defaultValue = "3") @Positive @Min(value = 1, message = "must greater than 0") Integer size
    ) {

        List<Venue> venues = venueService.getAllVenues(size, page);
        ApiResponse<List<Venue>> apiResponse = ApiResponse.<List<Venue>>builder()
                .message("All venues have been successfully fetched.")
                .payload(venues)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{venue-id}")
    @Operation(summary = "Get venue by ID")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") @Positive @Min(value = 1, message = "must greater than 0") Integer venueId) {
        Venue venue = venueService.getVenueById(venueId);

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("The venue has been successfully founded.")
                .payload(venue)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{venue-id}")
    @Operation(summary = "Update venue by ID")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(
            @PathVariable("venue-id") Integer venueId,
            @RequestBody @Valid VenueRequest venueRequest) {
        Venue venue = venueService.updateVenueById(venueId, venueRequest);

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("The venue has been successfully updated.")
                .payload(venue)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    @Operation(summary = "Add a new venue")
    public ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody @Valid VenueRequest venueRequest) {
        Venue venue = venueService.addVenue(venueRequest);

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("The venue has been successfully added.")
                .payload(venue)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{venue-id}")
    @Operation(summary = "Delete venue by ID")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") @Positive @Min(value = 1, message = "must greater than 0") Integer venueId) {
        Venue venue = venueService.DeleteVenueById(venueId);

        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("The venue has been successfully deleted.")
                .payload(venue)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
