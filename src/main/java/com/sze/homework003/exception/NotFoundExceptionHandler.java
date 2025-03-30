package com.sze.homework003.exception;

public class NotFoundExceptionHandler extends RuntimeException{
    public NotFoundExceptionHandler(String message) {
        super(message);
    }
}
