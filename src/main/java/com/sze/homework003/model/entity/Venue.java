package com.sze.homework003.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Venue {
    private Integer venueId;
    private String venueName;
    private String location;
}
