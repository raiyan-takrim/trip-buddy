package me.raiyantakrim.tripbuddy.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TripCreateReqDTO(
        UUID routeId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String busPlateNumber,
        BigDecimal basePrice
) {}
