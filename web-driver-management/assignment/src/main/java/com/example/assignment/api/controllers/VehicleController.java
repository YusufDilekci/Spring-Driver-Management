package com.example.assignment.api.controllers;

import com.example.assignment.business.abstracts.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    private VehicleService vehicleService;
    @GetMapping("/")
    public String getAll(Model model) {
        var vehicles = vehicleService.getAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/index.html";
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }
}
