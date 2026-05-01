package me.raiyantakrim.tripbuddy.controller;


import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.SeatDTO;
import me.raiyantakrim.tripbuddy.DTO.TripDTO;
import me.raiyantakrim.tripbuddy.entity.Trip;
import me.raiyantakrim.tripbuddy.service.TripService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/trips")
public class TripController {
    private final TripService tripService;
    /*###############################################
    *               GET Routes                     *
    ###############################################*/
    @GetMapping("/search")
    public ResponseEntity<List<Trip>> search(@RequestParam String origin,
                                             @RequestParam String destination,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Trip> trips = tripService.findAvailableTrips(origin, destination, date);
        if (trips.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(trips);
    }

    @GetMapping("/{tripId}/seats")
    public ResponseEntity<List<SeatDTO>> getSeats(@PathVariable UUID tripId) {
        return ResponseEntity.ok(tripService.getTripSeatMap(tripId));
    }
    /*###############################################
    *               POST Routes                     *
    ###############################################*/
    @PostMapping
    public ResponseEntity<Trip> saveTrip(@RequestBody TripDTO trip) {
        return ResponseEntity.ok().body(tripService.saveTrip(trip));
    }
}
