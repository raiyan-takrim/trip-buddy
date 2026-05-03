package me.raiyantakrim.tripbuddy.DTO;

import java.util.UUID;

public record UserDTO(
        UUID userId,
        String name,
        String email
) {}
