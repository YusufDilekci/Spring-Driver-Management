package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.AuthService;
import com.example.assignment.business.dtos.requests.LoginRequest;
import com.example.assignment.business.dtos.requests.RegisterRequest;
import com.example.assignment.business.dtos.responses.LoginResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private final AuthService authService;
    @GetMapping("/login")
    public String index() {
        return "login/index.html";
    }
    @PostMapping("/login")
    public String index(@Valid @ModelAttribute("loginRequest") LoginRequest request, HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(request);
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){

            // set accessToken to cookie header
            ResponseCookie cookie = ResponseCookie.from("token", loginResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(1800)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return "redirect:/";
        }

        return "redirect:/login";
    }
}
