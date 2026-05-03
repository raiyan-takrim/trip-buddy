package me.raiyantakrim.tripbuddy.repository;

import me.raiyantakrim.tripbuddy.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
