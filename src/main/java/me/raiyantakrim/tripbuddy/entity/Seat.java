package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat_id")
    private UUID seatId;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
    @Column(name = "seat_number")
    private String seatNumber;
    private SeatStatus status;
}
