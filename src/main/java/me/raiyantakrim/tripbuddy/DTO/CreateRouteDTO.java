package me.raiyantakrim.tripbuddy.DTO;

public record CreateRouteDTO(
        String origin,
        String destination,
        double distance
){}
