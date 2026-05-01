package me.raiyantakrim.tripbuddy.service;

import me.raiyantakrim.tripbuddy.entity.Trip;
import me.raiyantakrim.tripbuddy.repository.SeatRepository;
import me.raiyantakrim.tripbuddy.utility.SeatInitiator;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // Create Initial Seats for a new Trip
    public void initiateSeatForNewTrip(Trip trip) {
        seatRepository.saveAll(SeatInitiator.initiate(trip));
    }
}
