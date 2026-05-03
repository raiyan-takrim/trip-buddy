package me.raiyantakrim.tripbuddy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.BookingResponseDTO;
import me.raiyantakrim.tripbuddy.DTO.ReserveSeatRequestDTO;
import me.raiyantakrim.tripbuddy.entity.Booking;
import me.raiyantakrim.tripbuddy.entity.Seat;
import me.raiyantakrim.tripbuddy.entity.User;
import me.raiyantakrim.tripbuddy.repository.BookingRepository;
import me.raiyantakrim.tripbuddy.repository.SeatRepository;
import me.raiyantakrim.tripbuddy.repository.UserRepository;
import me.raiyantakrim.tripbuddy.utility.BookingStatus;
import me.raiyantakrim.tripbuddy.utility.SeatStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    @Transactional
    public BookingResponseDTO reserveSeat(ReserveSeatRequestDTO request){
        // Verify the User exits
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        // Get the seat with Database Lock.
        // If another user is currently accessing the row, this process will wait for that to finish.
        // If the seat is already LOCK or BOOKED this will return an empty optional
        Seat seat = seatRepository.findAvailableSeatByIdWithLock(request.seatId())
                .orElseThrow(() -> new RuntimeException("Conflict: Seat is already reserved or not exists"));
        // ==========================================
        // TESTING BLOCK: SIMULATE SLOW PROCESSING
        // ==========================================
//        try {
//            System.out.println("Thread " + Thread.currentThread().getId() + " locked the seat. Sleeping for 10 seconds...");
//            Thread.sleep(10000); // Pauses this specific thread for 10,000 milliseconds
//            System.out.println("Thread " + Thread.currentThread().getId() + " woke up! Finishing the transaction...");
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        // ==========================================

        // Verify the seat is actually belongs to the Trip
        if (!seat.getTrip().getId().equals(request.tripId())) {
            throw new RuntimeException("Invalid request: Seat does not belong to this trip");
        }
        // Update the Seat status to prevent others from claiming it
        seat.setStatus(SeatStatus.LOCKED);
        seatRepository.save(seat);

        //Create a pending Booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setTrip(seat.getTrip());
        booking.setStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(booking);

        LocalDateTime expiresAt = savedBooking.getCreatedAt().plusMinutes(10);

        return new BookingResponseDTO(
                savedBooking.getId(),
                seat.getSeatNumber(),
                savedBooking.getStatus().name(),
                expiresAt
        );

    }
}
