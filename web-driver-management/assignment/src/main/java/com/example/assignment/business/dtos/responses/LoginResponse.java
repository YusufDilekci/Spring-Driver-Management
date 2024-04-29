package com.example.assignment.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    public String token;
    public Authentication auth;
}
