package com.example.assignment.business.concretes;

import com.example.assignment.business.abstracts.DriverService;
import com.example.assignment.business.dtos.requests.*;
import com.example.assignment.business.dtos.responses.*;
import com.example.assignment.core.utilities.mappers.ModelMapperService;
import com.example.assignment.entities.Driver;
import com.example.assignment.repositories.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverManager implements DriverService{
    private final DriverRepository driverRepository;
    private final ModelMapperService modelMapperService;
    @Override
    public List<GetAllDriversResponse> getAll() {
        List<Driver> drivers = driverRepository.findAll();
        List<GetAllDriversResponse> driversResponse = drivers.stream()
                .map(driver -> this.modelMapperService.forResponse()
                        .map(driver, GetAllDriversResponse.class))
                .collect(Collectors.toList());

        return driversResponse;
    }

    @Override
    public CreatedDriverResponse add(CreateDriverRequest request) {
        Driver driver = this.modelMapperService.forRequest()
                .map(request, Driver.class);
        Driver savedDriver = driverRepository.save(driver);
        CreatedDriverResponse driverResponse = this.modelMapperService.forResponse()
                .map(savedDriver, CreatedDriverResponse.class);
        return driverResponse;
    }

    @Override
    public UpdatedDriverResponse update(UpdateDriverRequest request) {
        Driver driver = this.modelMapperService.forRequest()
                .map(request, Driver.class);

        Driver updatedDriver = driverRepository.save(driver);
        UpdatedDriverResponse updatedDriverResponse = this.modelMapperService.forResponse()
                .map(updatedDriver, UpdatedDriverResponse.class);
        return updatedDriverResponse;
    }

    @Override
    public DeletedDriverResponse delete(DeleteDriverRequest request) {
        var driver = driverRepository.findById(request.getId()).orElseThrow();
        driverRepository.deleteById(request.getId());

        DeletedDriverResponse deletedDriverResponse = this.modelMapperService.forResponse()
                .map(driver, DeletedDriverResponse.class);
        return deletedDriverResponse;
    }

    @Override
    public GetDriverByNameResponse getByName(GetDriverByNameRequest request) {
        Driver driver = driverRepository.findByName(request.getName());
        GetDriverByNameResponse driverResponse = this.modelMapperService.forResponse()
                .map(driver, GetDriverByNameResponse.class);
        return driverResponse;
    }

    @Override
    public GetDriverByIdResponse getById(GetDriverByIdRequest request) {
        Driver driver = driverRepository.findById(request.getId()).orElseThrow();
        GetDriverByIdResponse driverResponse = this.modelMapperService.forResponse()
                .map(driver, GetDriverByIdResponse.class);
        return driverResponse;
    }
}
