package com.churchofphilippi.webserver.exception.exceptionModel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MemberNotFoundException(String message) {
        super(message);
    }
}
