package com.churchofphilippi.webserver.exception;

import com.churchofphilippi.webserver.exception.exceptionModel.AuthenticationException;
import com.churchofphilippi.webserver.exception.exceptionModel.EmailTakenException;
import com.churchofphilippi.webserver.exception.exceptionModel.MemberNotFoundException;
import com.churchofphilippi.webserver.exception.exceptionModel.TokenNotTrustedException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Getter
@Setter
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private int NOT_FOUND = 404;
    private int BAD_REQUEST = 400;
    private String RESOURCE_NOT_FOUND = "Resource not found";
    private String AUTHENTICATION = "Authentication";

    @ExceptionHandler(MemberNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleMemberNotFoundException
            (MemberNotFoundException ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse(NOT_FOUND, RESOURCE_NOT_FOUND, message);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailTakenException.class)
    public final ResponseEntity<ErrorResponse> handleEmailTakenException
            (EmailTakenException ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, AUTHENTICATION, message);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorResponse> handleAuthenticationException
            (AuthenticationException ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, AUTHENTICATION, message);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotTrustedException.class)
    public final ResponseEntity<ErrorResponse> handleAuthenticationException
            (TokenNotTrustedException ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, AUTHENTICATION, message);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}