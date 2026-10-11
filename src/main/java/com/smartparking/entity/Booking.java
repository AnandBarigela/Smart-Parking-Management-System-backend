package com.smartparking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private ParkingSlot parkingSlot;

    private LocalDateTime bookingTime = LocalDateTime.now();
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.BOOKED;

    private double fee;

    public Booking() {}

    public Long getId() { return id; }
    @JsonIgnore
    public User getUser() { return user; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSlot getParkingSlot() { return parkingSlot; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public BookingStatus getStatus() { return status; }
    public double getFee() { return fee; }

    public void setUser(User user) { this.user = user; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public void setParkingSlot(ParkingSlot parkingSlot) { this.parkingSlot = parkingSlot; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public void setFee(double fee) { this.fee = fee; }
}
