package com.example.assignment.business.abstracts;

import com.example.assignment.business.dtos.requests.*;
import com.example.assignment.business.dtos.responses.*;

import java.util.List;


public interface UserService {
	List<GetAllUsersResponse> getAll();
	AssignedUserRolesResponse assignRoles(AssignUserRolesRequest request);
	CreatedUserResponse add(CreateUserRequest request);
	UpdatedUserResponse update(UpdateUserRequest request);
	DeletedUserResponse delete(DeleteUserRequest request);
	
}
