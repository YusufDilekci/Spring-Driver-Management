package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.AuthService;
import com.example.assignment.business.dtos.requests.LoginRequest;
import com.example.assignment.business.dtos.responses.LoginResponse;
import com.example.assignment.core.utilities.mappers.ModelMapperService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private final AuthService authService;
    private final ModelMapperService modelMapperService;
    @GetMapping("/login")
    public String index() {
        return "login/index.html";
    }
    @PostMapping("/login")
    public String index(@Valid @ModelAttribute LoginRequest request) {

        LoginResponse response = authService.login(request);
        if(response.auth.isAuthenticated()){
            return "redirect:/";
        }
        return "login/index.html";
    }
}
