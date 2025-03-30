package com.sze.homework003.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
