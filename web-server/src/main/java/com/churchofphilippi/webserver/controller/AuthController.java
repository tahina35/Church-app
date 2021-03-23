package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.model.Member;
import com.churchofphilippi.webserver.registration.RegistrationRequest;
import com.churchofphilippi.webserver.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/auth")
public class AuthController {

    private final MemberService memberService;

    @GetMapping("/forgot-password/{username}")
    public ResponseEntity<?> getMember(@PathVariable("username") String username) {
        return ResponseEntity.ok(memberService.loadUserByUsername(username));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.resetPassword(member));
    }
}
