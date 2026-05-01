package me.raiyantakrim.tripbuddy.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
// This record class is for search result
public record TripSearchDTO(UUID tripId,
                            String originCity,
                            String destinationCity,
                            Double distance,
                            LocalDateTime departureTime,
                            LocalDateTime arrivalTime,
                            String busPlateNumber,
                            BigDecimal basePrice
){}
