package com.smartparking.service;

import com.smartparking.dto.BookingRequest;
import com.smartparking.entity.*;
import com.smartparking.repository.*;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookings;
    private final UserRepository users;
    private final VehicleRepository vehicles;
    private final ParkingSlotRepository slots;

    public BookingService(BookingRepository bookings, UserRepository users,
                          VehicleRepository vehicles, ParkingSlotRepository slots) {
        this.bookings = bookings;
        this.users = users;
        this.vehicles = vehicles;
        this.slots = slots;
    }

    public List<Booking> byUser(Long userId) {
        return bookings.findByUserIdOrderByBookingTimeDesc(userId);
    }

    public Booking create(BookingRequest request) {
        User user = users.findById(request.userId()).orElseThrow();
        Vehicle vehicle = vehicles.findById(request.vehicleId()).orElseThrow();
        ParkingSlot slot = slots.findById(request.slotId()).orElseThrow();

        if (slot.getStatus() != SlotStatus.AVAILABLE)
            throw new IllegalStateException("Parking slot is not available");

        if (vehicle.getVehicleType() != slot.getVehicleType())
            throw new IllegalStateException("Vehicle type does not match this slot");

        Booking b = new Booking();
        b.setUser(user);
        b.setVehicle(vehicle);
        b.setParkingSlot(slot);

        slot.setStatus(SlotStatus.BOOKED);
        slots.save(slot);
        return bookings.save(b);
    }

    public Booking entry(Long id) {
        Booking b = bookings.findById(id).orElseThrow();
        if (b.getStatus() != BookingStatus.BOOKED)
            throw new IllegalStateException("Only booked parking can be checked in");

        b.setEntryTime(LocalDateTime.now());
        b.setStatus(BookingStatus.PARKED);

        ParkingSlot slot = b.getParkingSlot();
        slot.setStatus(SlotStatus.OCCUPIED);
        slots.save(slot);

        return bookings.save(b);
    }

    public Booking exit(Long id) {
        Booking b = bookings.findById(id).orElseThrow();
        if (b.getStatus() != BookingStatus.PARKED)
            throw new IllegalStateException("Vehicle is not currently parked");

        LocalDateTime exit = LocalDateTime.now();
        b.setExitTime(exit);
        b.setStatus(BookingStatus.COMPLETED);

        long minutes = Math.max(1, Duration.between(b.getEntryTime(), exit).toMinutes());
        long hours = Math.max(1, (minutes + 59) / 60);
        b.setFee(hours * b.getParkingSlot().getHourlyRate());

        ParkingSlot slot = b.getParkingSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        slots.save(slot);

        return bookings.save(b);
    }

    public Booking cancel(Long id) {
        Booking b = bookings.findById(id).orElseThrow();
        if (b.getStatus() != BookingStatus.BOOKED)
            throw new IllegalStateException("Only active bookings can be cancelled");

        b.setStatus(BookingStatus.CANCELLED);
        ParkingSlot slot = b.getParkingSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        slots.save(slot);

        return bookings.save(b);
    }
}
