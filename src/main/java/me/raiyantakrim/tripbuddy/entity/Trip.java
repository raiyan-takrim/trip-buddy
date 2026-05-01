package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id",  nullable = false)
    private Route route;
    @Column(name = "departure_time",  nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "arrival_time",  nullable = false)
    private LocalDateTime arrivalTime;
    @Column(name = "bus_plate_number",   nullable = false)
    private String busPlateNumber;
    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

}
