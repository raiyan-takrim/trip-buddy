package me.raiyantakrim.tripbuddy.controller;


import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.SeatDTO;
import me.raiyantakrim.tripbuddy.DTO.TripCreateReqDTO;
import me.raiyantakrim.tripbuddy.DTO.TripCreateResDTO;
import me.raiyantakrim.tripbuddy.DTO.TripSearchResDTO;
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
    public ResponseEntity<List<TripSearchResDTO>> search(@RequestParam String origin,
                                                         @RequestParam String destination,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<TripSearchResDTO> trips = tripService.findAvailableTrips(origin, destination, date);
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
    public ResponseEntity<TripCreateResDTO> saveTrip(@RequestBody TripCreateReqDTO trip) {
        return ResponseEntity.ok().body(tripService.saveTrip(trip));
    }
}
