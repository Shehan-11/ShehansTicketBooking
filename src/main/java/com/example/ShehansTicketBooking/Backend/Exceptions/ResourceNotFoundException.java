package com.example.ShehansTicketBooking.Backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long exceptionID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
