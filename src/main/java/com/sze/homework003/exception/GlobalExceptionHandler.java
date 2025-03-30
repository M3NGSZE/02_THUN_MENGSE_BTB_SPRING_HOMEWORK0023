package com.sze.homework003.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ProblemDetail handleException(NotFoundExceptionHandler e) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Not Found");
        detail.setDetail(e.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());

        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgument(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
//        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//
//            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//            System.out.println(fieldError.getDefaultMessage());
//        }
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        // Check if at least two errors exist before grouping
        if (fieldErrors.size() > 1
                && "must not be blank".equals(fieldErrors.get(0).getDefaultMessage())
                && "must not be blank".equals(fieldErrors.get(1).getDefaultMessage())) {

            // Combine the first two errors into one group message
            errors.put("fields", "Fields must not be blank");

            // Start processing from index 2 to avoid duplicate messages
            for (int i = 2; i < fieldErrors.size(); i++) {
                FieldError fieldError = fieldErrors.get(i);
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        } else {
            // Normal processing if the first two errors do not match the "must not be blank" case
            for (FieldError fieldError : fieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bad Request");
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);

        return detail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidation(HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();

        for(MessageSourceResolvable errorPath : e.getAllErrors()){
            for(String err : errorPath.getCodes()){
                if(err.contains("Min.page")){
                    errors.put("page", errorPath.getDefaultMessage());
                }else if(err.contains("Min.size")){
                    errors.put("size", errorPath.getDefaultMessage());
                }else if(err.contains("Min.venueId")){
                    errors.put("venueId", errorPath.getDefaultMessage());
                }
//                System.out.println(err);
            }
//            errors.put(errorPath.getCodes()[0], errorPath.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bad Request");
        detail.setProperty("timestamp", LocalDateTime.now());
        detail.setProperty("errors", errors);

        return detail;
    }
}
