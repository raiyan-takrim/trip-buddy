package me.raiyantakrim.tripbuddy.repository;

import me.raiyantakrim.tripbuddy.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
//    @Query("SELECT DISTINCT r FROM Route r JOIN Trip t ON t.route.routeId = r.routeId WHERE r.originCity = :origin AND r.destinationCity = :destination AND t.departureTime BETWEEN :startOfDay AND :endOfDay")
//    Optional<Route> findRouteWithActiveTripsOnDate(
//            @Param("origin") String origin,
//            @Param("destination") String destination,
//            @Param("startOfDay") LocalDateTime startOfDay,
//            @Param("endOfDay") LocalDateTime endOfDay
//            );
}
