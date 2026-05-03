package me.raiyantakrim.tripbuddy.repository;

import me.raiyantakrim.tripbuddy.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("SELECT b FROM Booking b WHERE b.status = PENDING AND b.createdAt <= :expirationTime")
    List<Booking> findExpiredBookings(@Param("expirationTime") LocalDateTime expirationTime);
}
