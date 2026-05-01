package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "origin_city",  nullable = false)
    private String originCity;

    @Column(name = "destination_city",   nullable = false)
    private String destinationCity;

    @Column(name = "distance_km")
    private double distance;
}
