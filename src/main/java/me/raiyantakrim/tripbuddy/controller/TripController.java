package me.raiyantakrim.tripbuddy.controller;


import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.entity.Trip;
import me.raiyantakrim.tripbuddy.service.TripService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/trips")
public class TripController {
    private final TripService tripService;

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
}
