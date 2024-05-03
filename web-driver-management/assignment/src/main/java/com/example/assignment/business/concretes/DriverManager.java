package com.example.assignment.business.concretes;

import com.example.assignment.business.abstracts.AssignmentService;
import com.example.assignment.business.abstracts.DriverService;
import com.example.assignment.business.abstracts.VehicleService;
import com.example.assignment.business.dtos.requests.*;
import com.example.assignment.business.dtos.responses.*;
import com.example.assignment.core.utilities.mappers.ModelMapperService;
import com.example.assignment.entities.Driver;
import com.example.assignment.entities.Vehicle;
import com.example.assignment.repositories.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverManager implements DriverService{
    private final DriverRepository driverRepository;
    private final ModelMapperService modelMapperService;
    private final VehicleService vehicleService;
    private final ResourceLoader resourceLoader;

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
    public GetAllDriversWithVehiclesResponse getAllWithVehicles() {
        List<Driver> drivers = driverRepository.findAll();
        List<GetAllVehiclesResponse> vehicles = vehicleService.getAll();
        GetAllDriversWithVehiclesResponse driversWithVehiclesResponse = new GetAllDriversWithVehiclesResponse();
        driversWithVehiclesResponse.setDrivers(drivers);
        driversWithVehiclesResponse.setVehicles(vehicles);


        return driversWithVehiclesResponse;
    }

    @Override
    public UploadedDriverImageResponse uploadImage(UploadDriverImageRequest request) throws IOException {
        var filename = saveImageToStorage(request.getImg());

        var driver = driverRepository.findById(request.getId()).orElseThrow();
        driver.setImgUrl(filename);

        Driver updatedDriver = driverRepository.save(driver);
        UploadedDriverImageResponse driverResponse = this.modelMapperService.forResponse()
                .map(updatedDriver, UploadedDriverImageResponse.class);

        return driverResponse;
    }

    private String saveImageToStorage(MultipartFile imageFile) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(resourceLoader.getResource("classpath:static/images").getURI());
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        imageFile.transferTo(filePath);

        return uniqueFileName;
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
        var oldDriver = driverRepository.findById(request.getId()).orElseThrow();
        Driver driver = this.modelMapperService.forRequest()
                .map(request, Driver.class);

        driver.setImgUrl(oldDriver.getImgUrl());
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
    public GetDriverByIdResponse getById(GetDriverByIdRequest request) {
        Driver driver = driverRepository.findById(request.getId()).orElseThrow();
        GetDriverByIdResponse driverResponse = this.modelMapperService.forResponse()
                .map(driver, GetDriverByIdResponse.class);
        return driverResponse;
    }

    @Override
    public GetDriverByNameResponse getByName(GetDriverByNameRequest request) {
        return null;
    }
}
