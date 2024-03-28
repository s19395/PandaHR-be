package com.s1935.pandahrbe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @RequestMapping("/")
    public @ResponseBody String helloWorld() {
        return "Hello, World";
    }

    @RequestMapping("/welcome")
    public @ResponseBody String welcome() {
        return "Welcome";
    }
}