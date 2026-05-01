package me.raiyantakrim.tripbuddy.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import me.raiyantakrim.tripbuddy.DTO.SeatDTO;
import me.raiyantakrim.tripbuddy.DTO.TripRequestDTO;
import me.raiyantakrim.tripbuddy.DTO.TripSearchDTO;
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
    public List<TripSearchDTO> findAvailableTrips(String origin, String destination, @NotNull LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<Trip> trips = tripRepository.searchTrips(origin, destination, startOfDay, endOfDay);
        return trips
                .stream()
                .map(trip -> new TripSearchDTO(trip.getId(),
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
                .toList();
    }

    /*###############################################
    *               CREATE method(s)                *
    ###############################################*/
    public Trip saveTrip(TripRequestDTO trip) {
        Route route = routeRepository.findById(trip.routeId()).orElseThrow(()-> new RuntimeException("Route not found"));
        Trip newTrip = new Trip();
        newTrip.setRoute(route);
        newTrip.setArrivalTime(trip.arrivalTime());
        newTrip.setDepartureTime(trip.departureTime());
        newTrip.setBasePrice(trip.basePrice());
        newTrip.setBusPlateNumber(trip.busPlateNumber());
        newTrip = tripRepository.save(newTrip);
        return seatService.initiateSeatForNewTrip(newTrip);
    }
}
