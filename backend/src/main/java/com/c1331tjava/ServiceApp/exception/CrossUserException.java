package com.c1331tjava.ServiceApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CrossUserException extends ResponseStatusException {

    public CrossUserException() {
        super(HttpStatus.FORBIDDEN, "Only ADMIN can view cross user info");
    }
}
