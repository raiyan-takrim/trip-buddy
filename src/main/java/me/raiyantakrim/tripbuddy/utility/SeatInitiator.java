package me.raiyantakrim.tripbuddy.utility;

import me.raiyantakrim.tripbuddy.entity.Seat;
import me.raiyantakrim.tripbuddy.entity.Trip;

import java.util.ArrayList;
import java.util.List;

public class SeatInitiator {
    /*
    * Helper methode that create seats for new trip.
    * Creation Strategy: 10 Rows (A-J) and 4 Columns(1 - 4),
    * seat number is combine of column number and row number eg: A1, H4.
    **/
    public static List<Seat> initiate(Trip trip) {
        List<Seat> seats = new ArrayList<>();
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        char[] columns = {'1', '2', '3', '4'};
        for (char row : rows) {
            for (char column : columns) {
                Seat seat = new Seat();
                String seatNumber = String.valueOf(row) + column;
                seat.setSeatNumber(seatNumber);
                seat.setTrip(trip);
                seat.setStatus(SeatStatus.AVAILABLE);
                seats.add(seat);
            }
        }
        return seats;
    }
}
