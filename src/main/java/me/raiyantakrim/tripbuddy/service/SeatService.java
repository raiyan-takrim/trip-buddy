package me.raiyantakrim.tripbuddy.service;

import me.raiyantakrim.tripbuddy.entity.Seat;
import me.raiyantakrim.tripbuddy.entity.Trip;
import me.raiyantakrim.tripbuddy.repository.SeatRepository;
import me.raiyantakrim.tripbuddy.utility.SeatInitiator;
import me.raiyantakrim.tripbuddy.utility.SeatStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // Create Initial Seats for a new Trip
    public Trip initiateSeatForNewTrip(Trip trip) {
        seatRepository.saveAll(SeatInitiator.initiate(trip));
        return trip;
    }
}
