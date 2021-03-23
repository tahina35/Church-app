package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.registration.RegistrationRequest;
import com.churchofphilippi.webserver.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.register(request));
    }
}
