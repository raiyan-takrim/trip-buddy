package me.raiyantakrim.tripbuddy.DTO;

public record ConfirmBookingRequestDTO(
        String paymentToken // In the real app this would come from a payment gateway
    ) {}
