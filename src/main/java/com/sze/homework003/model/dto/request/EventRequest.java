package com.sze.homework003.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EventRequest {
    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be blank")
    private String eventName;
    @NotNull(message = "must not be null")
    private LocalDateTime eventDate;
    @NotNull(message = "must not be null")
    @Min(value = 1, message = "must be greater than 0")
    private Integer venueId;
    @NotNull(message = "must not be null")
    @Size(min = 1, message = "must contain at least one attendee")
    private List<Integer> attendeeIds;
}
