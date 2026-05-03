package me.raiyantakrim.tripbuddy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.SeatDTO;
import me.raiyantakrim.tripbuddy.DTO.TripCreateReqDTO;
import me.raiyantakrim.tripbuddy.DTO.TripCreateResDTO;
import me.raiyantakrim.tripbuddy.DTO.TripSearchResDTO;
import me.raiyantakrim.tripbuddy.entity.Route;
import me.raiyantakrim.tripbuddy.entity.Trip;
import me.raiyantakrim.tripbuddy.repository.RouteRepository;
import me.raiyantakrim.tripbuddy.repository.SeatRepository;
import me.raiyantakrim.tripbuddy.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final RouteRepository routeRepository;
    private final SeatService seatService;

    /*###############################################
    *               SEARCH method(s)                *
    ###############################################*/
    public List<TripSearchResDTO> findAvailableTrips(String origin, String destination, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<Trip> trips = tripRepository.searchTrips(origin, destination, startOfDay, endOfDay);
        return trips
                .stream()
                .map(trip -> new TripSearchResDTO(trip.getId(),
                        trip.getRoute().getOriginCity(),
                        trip.getRoute().getDestinationCity(),
                        trip.getRoute().getDistance(),
                        trip.getDepartureTime(),
                        trip.getArrivalTime(),
                        trip.getBusPlateNumber(),
                        trip.getBasePrice()
                    ))
                .toList();
    }

    /*###############################################
    *               READ method(s)                *
    ###############################################*/
    public List<SeatDTO> getTripSeatMap(UUID tripId){
        return seatRepository.findByTripIdOrderByIdAsc(tripId)
                .stream()
                .map(seat -> new SeatDTO(
                        seat.getId(),
                        seat.getSeatNumber(),
                        seat.getStatus()
                ))
                // Sort seats by seat number in alphabetic order (A1, A2, A3, A4, B1, B2...)
                .sorted((s1,s2) -> s1.seatNumber().compareTo(s2.seatNumber()))
                .toList();
    }

    /*###############################################
    *               CREATE method(s)                *
    ###############################################*/
    @Transactional // To prevent trip creation if seat initiation fails
    public TripCreateResDTO saveTrip(TripCreateReqDTO trip) {
        Route route = routeRepository.findById(trip.routeId()).orElseThrow(()-> new RuntimeException("Route not found"));
        Trip newTrip = new Trip();
        newTrip.setRoute(route);
        newTrip.setArrivalTime(trip.arrivalTime());
        newTrip.setDepartureTime(trip.departureTime());
        newTrip.setBasePrice(trip.basePrice());
        newTrip.setBusPlateNumber(trip.busPlateNumber());

        Trip savedTrip = tripRepository.save(newTrip);

        // Creating available seats for the new trip
        seatService.initiateSeatForNewTrip(newTrip);

        return new TripCreateResDTO(
                savedTrip.getId(),
                savedTrip.getDepartureTime(),
                savedTrip.getArrivalTime(),
                savedTrip.getBusPlateNumber(),
                savedTrip.getBasePrice()
        );
    }
}
