package com.smartparking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus status = SlotStatus.AVAILABLE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private double hourlyRate;

    public ParkingSlot() {}

    public Long getId() { return id; }
    public String getSlotNumber() { return slotNumber; }
    public SlotStatus getStatus() { return status; }
    public VehicleType getVehicleType() { return vehicleType; }
    public double getHourlyRate() { return hourlyRate; }

    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    public void setStatus(SlotStatus status) { this.status = status; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
}
