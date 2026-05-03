package me.raiyantakrim.tripbuddy.DTO;

import java.util.UUID;

public record ReserveSeatRequestDTO(
        UUID userId,
        UUID tripId,
        UUID seatId
) {}
