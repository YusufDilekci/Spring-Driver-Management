package com.example.assignment.business.concretes;

import com.example.assignment.business.abstracts.VehicleService;
import com.example.assignment.business.dtos.requests.GetVehicleByBrandRequest;
import com.example.assignment.business.dtos.requests.GetVehicleByIdRequest;
import com.example.assignment.business.dtos.responses.GetAllVehiclesResponse;
import com.example.assignment.business.dtos.responses.GetVehicleByBrandResponse;
import com.example.assignment.business.dtos.responses.GetVehicleByIdResponse;
import com.example.assignment.core.utilities.mappers.ModelMapperService;
import com.example.assignment.entities.Vehicle;
import com.example.assignment.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleManager implements VehicleService {
    private VehicleRepository vehicleRepository;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllVehiclesResponse> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<GetAllVehiclesResponse> vehiclesResponse = vehicles.stream()
                .map(vehicle -> this.modelMapperService.forResponse()
                        .map(vehicle, GetAllVehiclesResponse.class))
                .collect(Collectors.toList());

        return vehiclesResponse;
    }

    @Override
    public GetVehicleByBrandResponse getByBrand(GetVehicleByBrandRequest request) {
        return null;
    }

    @Override
    public GetVehicleByIdResponse getById(GetVehicleByIdRequest request) {
        var vehicle = vehicleRepository.findById(request.getId()).orElseThrow();
        GetVehicleByIdResponse vehicleResponse = this.modelMapperService.forResponse()
                .map(vehicle, GetVehicleByIdResponse.class);
        return vehicleResponse;
    }
}
