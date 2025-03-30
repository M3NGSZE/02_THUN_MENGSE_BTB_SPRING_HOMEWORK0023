package com.sze.homework003.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VenueRequest {
    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be blank")
    private String venueName;
    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be blank")
    private String location;
}
