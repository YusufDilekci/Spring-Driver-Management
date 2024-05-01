package com.example.assignment.business.concretes;



import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.assignment.business.abstracts.UserService;
import com.example.assignment.business.dtos.requests.*;
import com.example.assignment.business.dtos.responses.*;
import com.example.assignment.core.entities.Role;
import com.example.assignment.core.entities.User;
import com.example.assignment.core.utilities.mappers.ModelMapperService;
import com.example.assignment.repositories.RoleRepository;
import com.example.assignment.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserManager implements UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ModelMapperService modelMapperService;
	private PasswordEncoder passwordEncoder;
	
	
	public List<GetAllUsersResponse> getAll() {
		var users = userRepository.findAll();
		
		List<GetAllUsersResponse> response= users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllUsersResponse.class))
				.collect(Collectors.toList());
		
		return response;
	}
	
	public GetUserByMailResponse getByEmail(GetUserByMailRequest request) {
		
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

		GetUserByMailResponse response = this.modelMapperService.forResponse().map(user, GetUserByMailResponse.class);
		
		return response;
	}
	
	public AssignedUserRolesResponse assignRoles(AssignUserRolesRequest request) {
		var user = userRepository.findByEmail(request.getEmail()).get();
		
		var roles = toSetRole(request.getRoles());

		Set<String> assignedRoles = new HashSet<>();

		for(Role r: roles) {
			var role = roleRepository.findByName(r.toString()).orElseThrow();
			user.addRole(role);

			assignedRoles.add(r.toString());
		}
		
		userRepository.save(user);

		AssignedUserRolesResponse response = new AssignedUserRolesResponse();
		response.setEmail(user.getEmail());
		response.setRoles(assignedRoles);

		return response;

	}

	@Override
	public CreatedUserResponse add(CreateUserRequest request) {
		User user  = new User();
		
		user.setRoles(toSetRole(request.getRoles()));
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		User newUser = userRepository.save(user);
		CreatedUserResponse response = this.modelMapperService.forResponse()
				.map(newUser, CreatedUserResponse.class);
		
		return response;
	}
	
	private Set<Role> toSetRole(String[] roles){
		Set<Role> arr= new HashSet<>();
		for(int i=0; i < roles.length; i++) {
			var role = roleRepository.findByName(roles[i]).orElseThrow();
			arr.add(role);
		}
		
		return arr;
		
	}

	@Override
	public UpdatedUserResponse update(UpdateUserRequest request) {

		User user = userRepository.findById(request.getId()).orElseThrow();
		
		user.setEmail(request.getEmail());
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setPhone(request.getPhone());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(toSetRole(request.getRoles()));
		
		User updatedUser = userRepository.save(user);

		UpdatedUserResponse response = this.modelMapperService.forResponse()
				.map(updatedUser, UpdatedUserResponse.class);

		return response;
	}

	@Override
	public DeletedUserResponse delete(DeleteUserRequest request) {
		var user = userRepository.findById(request.getId()).orElseThrow();
		userRepository.softdeleteById(user.getId());
		DeletedUserResponse response = this.modelMapperService.forResponse()
				.map(user, DeletedUserResponse.class);

		return response;
	}


}
