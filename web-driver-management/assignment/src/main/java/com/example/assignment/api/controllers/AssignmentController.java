package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.AssignmentService;
import com.example.assignment.business.abstracts.DriverService;
import com.example.assignment.business.abstracts.VehicleService;
import com.example.assignment.business.dtos.requests.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
@AllArgsConstructor
public class AssignmentController {
    private AssignmentService assignmentService;
    private DriverService driverService;
    private VehicleService vehicleService;

    @GetMapping("/")
    public String getAll(Model model){
        var assignments = assignmentService.getAll();
        var drivers = driverService.getAll();
        var vehicles = vehicleService.getAll();

        model.addAttribute("assignments", assignments);
        model.addAttribute("drivers", drivers);
        model.addAttribute("vehicles", vehicles);

        return "assignment/index.html";
    }
    @PostMapping("/add")

    public ResponseEntity<?> add(@RequestBody CreateAssignmentRequest request) {

        return ResponseEntity.ok(assignmentService.add(request));

    }

    @DeleteMapping("/delete")

    public ResponseEntity<?> delete(@RequestBody DeleteAssignmentRequest request) {
        return ResponseEntity.ok(assignmentService.delete(request));

    }

    @PutMapping("/update")

    public ResponseEntity<?> delete(@RequestBody UpdateAssignmentRequest request) {
        return ResponseEntity.ok(assignmentService.update(request));

    }



}
