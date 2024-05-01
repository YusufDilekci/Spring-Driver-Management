package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.UserService;
import com.example.assignment.business.dtos.requests.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    @PostMapping("/add")

    public ResponseEntity<?> add(@RequestBody CreateUserRequest request) {

        return ResponseEntity.ok(userService.add(request));

    }

    @DeleteMapping("/delete")

    public ResponseEntity<?> delete(@RequestBody DeleteUserRequest request) {
        return ResponseEntity.ok(userService.delete(request));

    }

    @PutMapping("/update")

    public ResponseEntity<?> delete(@RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(request));

    }
}
