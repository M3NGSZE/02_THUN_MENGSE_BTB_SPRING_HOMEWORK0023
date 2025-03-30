package com.sze.homework003.model.dto.response;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;

}
