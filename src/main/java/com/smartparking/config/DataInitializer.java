package com.smartparking.config;

import com.smartparking.entity.*;
import com.smartparking.repository.ParkingSlotRepository;
import com.smartparking.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository users,
                           ParkingSlotRepository slots,
                           PasswordEncoder encoder) {
        return args -> {
            if (users.findByEmail("admin@parking.com").isEmpty()) {
                users.save(new User(
                        "System Admin",
                        "admin@parking.com",
                        encoder.encode("admin123"),
                        Role.ADMIN
                ));
            }

            if (slots.count() == 0) {
                for (int i = 1; i <= 8; i++) {
                    ParkingSlot s = new ParkingSlot();
                    s.setSlotNumber("C-" + String.format("%02d", i));
                    s.setVehicleType(VehicleType.CAR);
                    s.setHourlyRate(30);
                    s.setStatus(SlotStatus.AVAILABLE);
                    slots.save(s);
                }

                for (int i = 1; i <= 4; i++) {
                    ParkingSlot s = new ParkingSlot();
                    s.setSlotNumber("B-" + String.format("%02d", i));
                    s.setVehicleType(VehicleType.BIKE);
                    s.setHourlyRate(15);
                    s.setStatus(SlotStatus.AVAILABLE);
                    slots.save(s);
                }
            }
        };
    }
}
