package com.example.assignment.repositories;

import com.example.assignment.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(@Param("roleName") String roleName);

}