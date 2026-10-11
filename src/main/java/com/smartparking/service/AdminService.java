package com.smartparking.service;

import com.smartparking.repository.*;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AdminService {
    private final UserRepository users;
    private final VehicleRepository vehicles;
    private final ParkingSlotRepository slots;
    private final BookingRepository bookings;

    public AdminService(UserRepository users, VehicleRepository vehicles,
                        ParkingSlotRepository slots, BookingRepository bookings) {
        this.users = users;
        this.vehicles = vehicles;
        this.slots = slots;
        this.bookings = bookings;
    }

    public Map<String, Long> dashboard() {
        return Map.of(
                "users", users.count(),
                "vehicles", vehicles.count(),
                "slots", slots.count(),
                "bookings", bookings.count()
        );
    }
}
