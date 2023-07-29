package com.dolphin.controller;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    public ErrorResponse(String message) {
    	this.message=message;
    }

    // Constructor, getters, and setters
}
