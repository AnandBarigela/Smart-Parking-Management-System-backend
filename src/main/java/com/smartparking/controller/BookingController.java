package com.smartparking.controller;

import com.smartparking.dto.BookingRequest;
import com.smartparking.entity.Booking;
import com.smartparking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public List<Booking> byUser(@PathVariable Long userId) {
        return service.byUser(userId);
    }

    @PostMapping
    public Booking create(@Valid @RequestBody BookingRequest request) {
        return service.create(request);
    }

    @PostMapping("/{id}/entry")
    public Booking entry(@PathVariable Long id) {
        return service.entry(id);
    }

    @PostMapping("/{id}/exit")
    public Booking exit(@PathVariable Long id) {
        return service.exit(id);
    }

    @PutMapping("/{id}/cancel")
    public Booking cancel(@PathVariable Long id) {
        return service.cancel(id);
    }
}
