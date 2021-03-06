package com.churchofphilippi.webserver.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    public ResponseEntity<Error> handleError(HttpServletRequest request) throws Throwable {
        Object exception = request.getAttribute("javax.servlet.error.exception");

        // TODO: Logic to inspect exception thrown from Filters...
        return ResponseEntity.badRequest().body(new Error("error"));
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
