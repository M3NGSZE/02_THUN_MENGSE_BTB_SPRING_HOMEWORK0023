package com.sze.homework003.model.dto.request.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime time;
}
