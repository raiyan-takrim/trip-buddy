package me.raiyantakrim.tripbuddy.DTO;

import java.util.UUID;

public record CreateRouteResDTO(
        UUID routeId,
        String originCity,
        String destinationCity,
        double distance
) {}
