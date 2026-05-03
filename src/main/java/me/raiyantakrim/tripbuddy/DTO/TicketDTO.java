package me.raiyantakrim.tripbuddy.DTO;

import me.raiyantakrim.tripbuddy.utility.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketDTO(
        UUID bookingId,
        BookingStatus status,
        LocalDateTime bookingTimestamp,
        UserDTO user,
        TripCreateResDTO trip,
        CreateRouteResDTO route,
        SeatDTO seat
) {}
