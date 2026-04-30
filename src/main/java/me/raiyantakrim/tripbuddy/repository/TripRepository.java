package me.raiyantakrim.tripbuddy.repository;

import me.raiyantakrim.tripbuddy.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    @Query("SELECT t FROM Trip t " +
            "WHERE t.route.originCity = :origin " +
            "AND t.route.destinationCity = :destination " +
            "AND t.departureTime BETWEEN :startOfDay AND :endOfDay")
    List<Trip> searchTrips(@Param("origin") String origin,
                           @Param("destination") String destination,
                           @Param("startOfDay") LocalDateTime startOfDay,
                           @Param("endOfDay") LocalDateTime endOfDay
                           );
}
