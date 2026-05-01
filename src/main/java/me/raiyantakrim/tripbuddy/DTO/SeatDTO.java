package me.raiyantakrim.tripbuddy.DTO;

import me.raiyantakrim.tripbuddy.utility.SeatStatus;

import java.util.UUID;

public record SeatDTO(UUID id, String seatNumber, SeatStatus status){};
