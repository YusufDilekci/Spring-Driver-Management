package com.example.assignment.business.dtos.responses;

import java.util.Set;

import com.example.assignment.core.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersResponse {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String imgUrl;
	private Set<Role> roles;
}
