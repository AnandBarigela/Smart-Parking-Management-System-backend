package com.smartparking.controller;

import com.smartparking.dto.VehicleRequest;
import com.smartparking.entity.Vehicle;
import com.smartparking.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public List<Vehicle> byUser(@PathVariable Long userId) {
        return service.byUser(userId);
    }

    @PostMapping("/user/{userId}")
    public Vehicle create(@PathVariable Long userId,
                          @Valid @RequestBody VehicleRequest request) {
        return service.create(userId, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
