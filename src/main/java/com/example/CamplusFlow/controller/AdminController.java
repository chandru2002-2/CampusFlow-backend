package com.example.CamplusFlow.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String admin() {
        return "Welcome Admin 🔥";
    }
}