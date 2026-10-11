package com.smartparking.service;

import com.smartparking.entity.ParkingSlot;
import com.smartparking.entity.SlotStatus;
import com.smartparking.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParkingSlotService {
    private final ParkingSlotRepository slots;

    public ParkingSlotService(ParkingSlotRepository slots) {
        this.slots = slots;
    }

    public List<ParkingSlot> all() { return slots.findAll(); }

    public List<ParkingSlot> available() {
        return slots.findByStatus(SlotStatus.AVAILABLE);
    }

    public ParkingSlot create(ParkingSlot slot) {
        slot.setStatus(SlotStatus.AVAILABLE);
        return slots.save(slot);
    }

    public ParkingSlot update(Long id, ParkingSlot input) {
        ParkingSlot s = slots.findById(id).orElseThrow();
        s.setSlotNumber(input.getSlotNumber());
        s.setVehicleType(input.getVehicleType());
        s.setHourlyRate(input.getHourlyRate());
        s.setStatus(input.getStatus());
        return slots.save(s);
    }

    public void delete(Long id) { slots.deleteById(id); }
}
