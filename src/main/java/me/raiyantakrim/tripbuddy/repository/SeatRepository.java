package me.raiyantakrim.tripbuddy.repository;

import me.raiyantakrim.tripbuddy.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findByTripIdOrderByIdAsc(UUID tripId);
}
