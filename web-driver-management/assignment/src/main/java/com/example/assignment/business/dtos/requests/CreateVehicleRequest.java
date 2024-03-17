package com.example.assignment.business.dtos.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {
    private String brand;

    private String model;

    private String color;

    private String type;
}
