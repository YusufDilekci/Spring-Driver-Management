package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.AssignmentService;
import com.example.assignment.business.dtos.requests.CreateAssignmentRequest;
import com.example.assignment.business.dtos.requests.CreateDriverRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assignments")
@AllArgsConstructor
public class AssignmentController {
    private AssignmentService assignmentService;

    @GetMapping("/")
    public String getAllWithDriverAndVehicle(Model model){
        var assignments = assignmentService.getAllWithDriverAndVehicle();
        model.addAttribute("assignments", assignments);
        return "assignment/index.html";
    }
    @PostMapping("/add")

    public ResponseEntity<?> add(@RequestBody CreateAssignmentRequest request) {

        return ResponseEntity.ok(assignmentService.add(request));

    }

}
