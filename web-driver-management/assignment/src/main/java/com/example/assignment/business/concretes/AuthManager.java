package com.example.assignment.business.concretes;

import com.example.assignment.business.abstracts.AuthService;
import com.example.assignment.business.dtos.requests.LoginRequest;
import com.example.assignment.business.dtos.requests.RegisterRequest;

import com.example.assignment.business.dtos.responses.LoginResponse;
import com.example.assignment.business.dtos.responses.RegisterResponse;
import com.example.assignment.core.entities.User;
import com.example.assignment.core.security.jwt.JwtService;
import com.example.assignment.repositories.RoleRepository;
import com.example.assignment.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        var role = roleRepository.findByName("USER").orElseThrow();

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .roles(Set.of(role))
                .deleted(false)
                .build();


        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegisterResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();

    }


}
