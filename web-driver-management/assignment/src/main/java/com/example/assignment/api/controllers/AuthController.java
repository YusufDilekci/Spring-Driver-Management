package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.AuthService;
import com.example.assignment.business.dtos.requests.LoginRequest;
import com.example.assignment.business.dtos.requests.RegisterRequest;
import com.example.assignment.business.dtos.responses.LoginResponse;
import com.example.assignment.business.dtos.responses.RegisterResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		return ResponseEntity.ok(authService.login(request));
	}
	
}
