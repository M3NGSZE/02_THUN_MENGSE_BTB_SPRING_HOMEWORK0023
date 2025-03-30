package com.sze.homework003.controller;

import com.sze.homework003.model.dto.request.AttendeeRequest;
import com.sze.homework003.model.dto.request.EventRequest;
import com.sze.homework003.model.entity.Attendee;
import com.sze.homework003.model.entity.Event;
import com.sze.homework003.model.entity.Venue;
import com.sze.homework003.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sze.homework003.model.dto.request.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "event-controller")
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @Operation(summary = "Get all events")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(
            @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "must greater than 0") Integer page,
            @RequestParam(defaultValue = "3") @Positive @Min(value = 1, message = "must greater than 0") Integer size
    ) {
        List<Event> events = eventService.getAllEvents(size, page);
        ApiResponse<List<Event>> apiResponse = ApiResponse.<List<Event>>builder()
                .message("All events have been successfully fetched.")
                .payload(events)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{event-id}")
    @Operation(summary = "Get event by ID")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") @Positive @Min(value = 1, message = "must greater than 0") Integer eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("The event has been successfully founded.")
                .payload(event)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    @Operation(summary = "Add a new event")
    public ResponseEntity<ApiResponse<Event>> addEvent(@RequestBody @Valid EventRequest eventRequest) {
        Event event = eventService.addEvent(eventRequest);

        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("The event has been successfully added.")
                .payload(event)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{event-id}")
    @Operation(summary = "Update attendee by ID")
    public ResponseEntity<ApiResponse<Event>> updateEventById(
            @PathVariable("event-id") Integer eventId,
            @RequestBody @Valid EventRequest eventRequest) {
        Event event = eventService.updateEventById(eventId, eventRequest);

        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("The event has been successfully updated.")
                .payload(event)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{event-id}")
    @Operation(summary = "Delete venue by ID")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") @Positive @Min(value = 1, message = "must greater than 0") Integer eventId) {
        Event event = eventService.deleteEventById(eventId);

        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("The event has been successfully deleted.")
                .payload(event)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
