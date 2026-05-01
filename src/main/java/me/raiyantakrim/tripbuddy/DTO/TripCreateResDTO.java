package me.raiyantakrim.tripbuddy.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TripCreateResDTO(
        UUID tripId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String busPlateNumber,
        BigDecimal basePrice
) {}
