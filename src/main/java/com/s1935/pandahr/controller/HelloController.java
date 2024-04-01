package com.s1935.pandahr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Azure Spring Apps! ";
    }

    @GetMapping("/messages")
    public ResponseEntity<List<String>> hello() {
        return ResponseEntity.ok(Arrays.asList("Hello", "World"));
    }
}