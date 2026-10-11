package com.smartparking.dto;

import jakarta.validation.constraints.NotNull;

public record BookingRequest(
    @NotNull Long userId,
    @NotNull Long vehicleId,
    @NotNull Long slotId
) {}
