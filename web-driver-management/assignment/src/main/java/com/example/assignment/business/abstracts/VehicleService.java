package com.example.assignment.business.abstracts;

import com.example.assignment.business.dtos.requests.GetVehicleByBrandRequest;
import com.example.assignment.business.dtos.requests.GetVehicleByIdRequest;
import com.example.assignment.business.dtos.responses.GetAllVehiclesResponse;
import com.example.assignment.business.dtos.responses.GetVehicleByBrandResponse;
import com.example.assignment.business.dtos.responses.GetVehicleByIdResponse;

import java.util.List;

public interface VehicleService {
    List<GetAllVehiclesResponse> getAll();
    GetVehicleByIdResponse getById(GetVehicleByIdRequest request);
}
