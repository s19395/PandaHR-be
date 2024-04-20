package com.s1935.pandahr.controller;

import com.s1935.pandahr.infrastructure.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class HelloController {

    private final EmailService emailService;

    @RequestMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }

    @GetMapping("/messages")
    public ResponseEntity<String> hello(Principal principal) {
        return ResponseEntity.ok(principal.getName() + " is accessing the messages");
    }

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendEmail() {
        emailService.sendEmail();
        return ResponseEntity.ok("Email sent!");
    }
}