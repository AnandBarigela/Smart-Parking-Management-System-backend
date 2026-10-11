package com.smartparking.controller;

import com.smartparking.entity.ParkingSlot;
import com.smartparking.service.ParkingSlotService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {
    private final ParkingSlotService service;

    public ParkingSlotController(ParkingSlotService service) {
        this.service = service;
    }

    @GetMapping
    public List<ParkingSlot> all() {
        return service.all();
    }

    @GetMapping("/available")
    public List<ParkingSlot> available() {
        return service.available();
    }

    @PostMapping
    public ParkingSlot create(@RequestBody ParkingSlot slot) {
        return service.create(slot);
    }

    @PutMapping("/{id}")
    public ParkingSlot update(@PathVariable Long id,
                              @RequestBody ParkingSlot slot) {
        return service.update(id, slot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
