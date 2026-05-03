package me.raiyantakrim.tripbuddy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.entity.Booking;
import me.raiyantakrim.tripbuddy.entity.Seat;
import me.raiyantakrim.tripbuddy.repository.BookingRepository;
import me.raiyantakrim.tripbuddy.repository.SeatRepository;
import me.raiyantakrim.tripbuddy.utility.BookingStatus;
import me.raiyantakrim.tripbuddy.utility.SeatStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingCleanupService{
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void releaseExpiredHolds(){
        // Calculate the 10 min ago time
        LocalDateTime tenMinuteAgo = LocalDateTime.now().minusMinutes(10);

        // Fetch all expired Bookings
        List<Booking> expiredBookings = bookingRepository.findExpiredBookings(tenMinuteAgo);
        if(expiredBookings.isEmpty()){
            return;
        }

        System.out.println("Found " +  expiredBookings.size() + " expired bookings. Cleaning up...");

        // Loop through and release the seats
        for(Booking booking : expiredBookings){
            // Mark the Booking as CANCELLED
            booking.setStatus(BookingStatus.CANCELLED);

            // Get the associate Seat and make it back to AVAILABLE
            Seat seat = booking.getSeat();
            seat.setStatus(SeatStatus.AVAILABLE);

            // Save the updates
            seatRepository.save(seat);
            bookingRepository.save(booking);

            System.out.println("Released seat " + seat.getSeatNumber() + " for booking ID: " + booking.getId());
        }
    }
}
