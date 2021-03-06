package com.churchofphilippi.webserver.controller;

import com.churchofphilippi.webserver.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<?> getMembers() {
        return ResponseEntity.ok(memberService.getAll());
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("hello");
    }

}
