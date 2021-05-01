package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.FCMToken;
import com.churchofphilippi.webserver.service.FCMTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/fcmtoken")
public class FCMTokenController {

    private final FCMTokenService fcmTokenService;

    @PostMapping
    public ResponseEntity<?> update(@RequestBody FCMToken token) {
        return ResponseEntity.ok(fcmTokenService.updateToken(token));
    }


}
