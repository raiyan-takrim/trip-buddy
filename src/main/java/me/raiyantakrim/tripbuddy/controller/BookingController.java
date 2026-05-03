package me.raiyantakrim.tripbuddy.controller;

import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.BookingResponseDTO;
import me.raiyantakrim.tripbuddy.DTO.ReserveSeatRequestDTO;
import me.raiyantakrim.tripbuddy.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/reserve")
    public ResponseEntity<BookingResponseDTO> reserveBooking(@RequestBody ReserveSeatRequestDTO reserveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.reserveSeat(reserveRequest));
    }
}
