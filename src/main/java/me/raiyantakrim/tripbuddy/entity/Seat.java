package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import me.raiyantakrim.tripbuddy.utility.SeatStatus;

import java.util.UUID;
@Data
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id",   nullable = false)
    private Trip trip;
    @Column(name = "seat_number",   nullable = false)
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;
}
