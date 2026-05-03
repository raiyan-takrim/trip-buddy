package me.raiyantakrim.tripbuddy.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDTO(
        UUID bookingId,
        String seatNumber,
        String bookingStatus,
        LocalDateTime expiresAt
){}
