package com.dolphin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        // Log the exception
        // You can use a logging framework like SLF4J to log the exception.

        // Create a custom error response
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred. Please try again later.");

        // Return the custom error response and an appropriate HTTP status code
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
