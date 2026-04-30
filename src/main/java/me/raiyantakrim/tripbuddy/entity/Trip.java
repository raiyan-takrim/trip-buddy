package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "trip_id")
    private UUID tripId;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    @Column(name = "bus_plate_number")
    private String busPlateNumber;
    @Column(name = "base_price")
    private double basePrice;

}
