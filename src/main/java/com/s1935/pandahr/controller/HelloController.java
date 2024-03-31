package com.s1935.pandahr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Azure Spring Apps! THIS IS THE GREEN DEPLOYMENT";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello azure!";
    }
}