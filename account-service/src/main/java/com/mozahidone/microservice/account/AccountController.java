package com.mozahidone.microservice.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}