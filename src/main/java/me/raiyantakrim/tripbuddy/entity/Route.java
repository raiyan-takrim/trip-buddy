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
    @Column(name = "route_id")
    private UUID routeId;
    @Column(name = "origin_city")
    private String originCity;
    @Column(name = "destination_city")
    private String destinationCity;
    private double distance;
}
