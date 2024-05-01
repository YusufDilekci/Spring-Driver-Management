package com.example.assignment.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String phone;
	private String[] roles;
	
}
