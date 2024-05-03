package com.example.assignment.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
	
	private int id;
	private String phone;
	private String username;
	private String password;
	private String[] roles;
}
