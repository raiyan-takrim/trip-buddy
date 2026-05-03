package me.raiyantakrim.tripbuddy.controller;

import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.BookingResponseDTO;
import me.raiyantakrim.tripbuddy.DTO.ConfirmBookingRequestDTO;
import me.raiyantakrim.tripbuddy.DTO.ReserveSeatRequestDTO;
import me.raiyantakrim.tripbuddy.DTO.TicketDTO;
import me.raiyantakrim.tripbuddy.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/reserve")
    public ResponseEntity<BookingResponseDTO> reserveBooking(@RequestBody ReserveSeatRequestDTO reserveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.reserveSeat(reserveRequest));
    }
    @PostMapping("/{bookingId}/confirm")
    public ResponseEntity<TicketDTO> confirmBooking(@PathVariable UUID bookingId,
                                                    ConfirmBookingRequestDTO request) {
        TicketDTO confirmedTicket = bookingService.confirmBooking(bookingId, request);
        return ResponseEntity.ok(confirmedTicket);
    }
}
