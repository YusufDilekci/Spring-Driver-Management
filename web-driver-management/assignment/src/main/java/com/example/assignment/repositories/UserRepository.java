package com.example.assignment.repositories;

import com.example.assignment.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query(
            value = "UPDATE users SET deleted = true where id = :userId",
            nativeQuery = true)
    void softdeleteById(@Param("userId") int userId);


}
