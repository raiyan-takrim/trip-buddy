package me.raiyantakrim.tripbuddy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.*;
import me.raiyantakrim.tripbuddy.entity.*;
import me.raiyantakrim.tripbuddy.exception.InvalidBookingStateException;
import me.raiyantakrim.tripbuddy.exception.ResourceNotFoundException;
import me.raiyantakrim.tripbuddy.exception.SeatAlreadyReservedException;
import me.raiyantakrim.tripbuddy.repository.BookingRepository;
import me.raiyantakrim.tripbuddy.repository.SeatRepository;
import me.raiyantakrim.tripbuddy.repository.UserRepository;
import me.raiyantakrim.tripbuddy.utility.BookingStatus;
import me.raiyantakrim.tripbuddy.utility.SeatStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    @Transactional
    public BookingResponseDTO reserveSeat(ReserveSeatRequestDTO request){
        // Verify the User exits
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Get the seat with Database Lock.
        // If another user is currently accessing the row, this process will wait for that to finish.
        // If the seat is already LOCK or BOOKED this will return an empty optional
        Seat seat = seatRepository.findAvailableSeatByIdWithLock(request.seatId())
                .orElseThrow(() -> new SeatAlreadyReservedException("Conflict: Seat is already reserved or not exists"));
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
            throw new ResourceNotFoundException("Seat does not belong to this trip");
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
    @Transactional
    public TicketDTO confirmBooking(UUID bookingId, ConfirmBookingRequestDTO request) {

        // 1. Fetch the booking
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking not found"));
        // 2. Check if EXPIRED
        if (booking.getStatus().equals(BookingStatus.CANCELLED)){
            throw new InvalidBookingStateException("Your reservation has expired. Please search for a new seat.");
        }
        // 3. Check if already CONFIRMED
        if (booking.getStatus().equals(BookingStatus.CONFIRMED)){
            throw new InvalidBookingStateException("This booking has already been confirmed.");
        }
        // 4. Simulate payment process
        if (request.paymentToken() == null || request.paymentToken().isEmpty()) {
            throw new IllegalArgumentException("Payment token is required");
        }
        System.out.println("Processing payment token: " + request.paymentToken() + "... Payment Successful!");
        // 5. Update status
        booking.setStatus(BookingStatus.CONFIRMED);

        Seat seat = booking.getSeat();
        seat.setStatus(SeatStatus.BOOKED); // Permanently booking the seat
        // 6. Save changes
        seatRepository.save(seat);
        Booking savedBooking = bookingRepository.save(booking);
        // 7. Return ticket
        Trip trip =  booking.getTrip();
        User user = booking.getUser();
        Route route = trip.getRoute();
        return new TicketDTO(
                booking.getId(),
                booking.getStatus(),
                LocalDateTime.now(),
                new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ),
                new TripCreateResDTO(
                        trip.getId(),
                        trip.getDepartureTime(),
                        trip.getArrivalTime(),
                        trip.getBusPlateNumber(),
                        trip.getBasePrice()
                ),
                new CreateRouteResDTO(
                        route.getId(),
                        route.getOriginCity(),
                        route.getDestinationCity(),
                        route.getDistance()
                ),
                new SeatDTO(
                        seat.getId(),
                        seat.getSeatNumber(),
                        seat.getStatus()
                )
        );
    }
}
