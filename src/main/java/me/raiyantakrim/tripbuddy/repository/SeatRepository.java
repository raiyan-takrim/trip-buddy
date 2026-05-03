package me.raiyantakrim.tripbuddy.repository;

import jakarta.persistence.LockModeType;
import me.raiyantakrim.tripbuddy.entity.Booking;
import me.raiyantakrim.tripbuddy.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findByTripIdOrderByIdAsc(UUID tripId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Seat s WHERE s.id = :id AND s.status = AVAILABLE")
    Optional<Seat> findAvailableSeatByIdWithLock(@Param("id") UUID id);
}
