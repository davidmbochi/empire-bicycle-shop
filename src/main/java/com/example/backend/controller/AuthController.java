package com.example.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/not-authorized")
    public String authorizationError(){
        return "auth/auth-error";
    }
}
