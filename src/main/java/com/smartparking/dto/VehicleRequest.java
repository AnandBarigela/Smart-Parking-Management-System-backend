package com.smartparking.dto;

import com.smartparking.entity.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequest(
    @NotBlank String vehicleNumber,
    @NotNull VehicleType vehicleType
) {}
