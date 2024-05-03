package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.AuthService;
import com.example.assignment.business.dtos.requests.RegisterRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegisterController {
    private final AuthService authService;
    @GetMapping("/register")
    public String index() {
        return "register/index.html";
    }
    @PostMapping("/register")
    public String index(@Valid @ModelAttribute("registerRequest") RegisterRequest request) {
        authService.register(request);
        return "redirect:/login";
    }
}
