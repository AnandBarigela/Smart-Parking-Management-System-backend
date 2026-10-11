package com.smartparking.service;

import com.smartparking.dto.VehicleRequest;
import com.smartparking.entity.User;
import com.smartparking.entity.Vehicle;
import com.smartparking.repository.UserRepository;
import com.smartparking.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicles;
    private final UserRepository users;

    public VehicleService(VehicleRepository vehicles, UserRepository users) {
        this.vehicles = vehicles;
        this.users = users;
    }

    public List<Vehicle> byUser(Long userId) {
        return vehicles.findByUserId(userId);
    }

    public Vehicle create(Long userId, VehicleRequest request) {
        if (vehicles.existsByVehicleNumber(request.vehicleNumber()))
            throw new IllegalArgumentException("Vehicle number already registered");

        User user = users.findById(userId).orElseThrow();
        Vehicle v = new Vehicle();
        v.setVehicleNumber(request.vehicleNumber().toUpperCase());
        v.setVehicleType(request.vehicleType());
        v.setUser(user);
        return vehicles.save(v);
    }

    public void delete(Long id) {
        vehicles.deleteById(id);
    }
}
