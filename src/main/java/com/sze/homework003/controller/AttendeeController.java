package com.sze.homework003.controller;

import com.sze.homework003.model.dto.request.AttendeeRequest;
import com.sze.homework003.model.entity.Attendee;
import com.sze.homework003.service.AttendeeService;
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
@Tag(name = "attendee-controller")
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    @Operation(summary = "Get all attendees")
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "must greater than 0") Integer page,
            @RequestParam(defaultValue = "3") @Positive @Min(value = 1, message = "must greater than 0") Integer size
    ) {
        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);
        ApiResponse<List<Attendee>> apiResponse = ApiResponse.<List<Attendee>>builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendees)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{attendee-id}")
    @Operation(summary = "Get attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") @Positive @Min(value = 1, message = "must greater than 0") Integer attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully founded.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    @Operation(summary = "Add a new attendee")
    public ResponseEntity<ApiResponse<Attendee>> addAttendee(@RequestBody @Valid AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.addAttendee(attendeeRequest);

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully added.")
                .payload(attendee)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{attendee-id}")
    @Operation(summary = "Update attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(
            @PathVariable("attendee-id") Integer attendeeId,
            @RequestBody @Valid AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId, attendeeRequest);

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully updated.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{attendee-id}")
    @Operation(summary = "Delete attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee-id") @Positive @Min(value = 1, message = "must greater than 0") Integer attendeeId) {
        Attendee attendee = attendeeService.deleteAttendeeById(attendeeId);

        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("The venue has been successfully deleted.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
