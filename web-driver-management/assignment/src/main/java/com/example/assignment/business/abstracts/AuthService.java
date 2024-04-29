package com.example.assignment.business.abstracts;

import com.example.assignment.business.dtos.requests.LoginRequest;
import com.example.assignment.business.dtos.requests.RegisterRequest;
import com.example.assignment.business.dtos.responses.LoginResponse;
import com.example.assignment.business.dtos.responses.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
