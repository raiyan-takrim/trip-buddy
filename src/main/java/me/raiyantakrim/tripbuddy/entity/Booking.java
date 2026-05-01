package me.raiyantakrim.tripbuddy.entity;

import jakarta.persistence.*;
import lombok.Data;
import me.raiyantakrim.tripbuddy.utility.BookingStatus;

import java.util.List;
import java.util.UUID;
@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID bookingId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
    @OneToMany
    @JoinColumn(name = "seat_id")
    private List<Seat> seat;
    private BookingStatus status;
}
