package com.churchofphilippi.webserver.exception.exceptionModel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenNotTrustedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenNotTrustedException(String message) {
        super(message);
    }
}
