package com.example.parkingsystem.service;

import com.example.parkingsystem.model.Booking;
import com.example.parkingsystem.model.ParkingSpot;




import com.example.parkingsystem.Repository.BookingRepository;
import com.example.parkingsystem.Repository.ParkingSpotRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public Booking createBooking(Booking booking) throws Exception {
        ParkingSpot parkingSpot = booking.getParkingSpot();

        if (parkingSpot.getStatus() == ParkingSpot.Status.OCCUPIED) {
            throw new Exception("Parking spot is already occupied.");
        }

        // Mark the parking spot as occupied
        parkingSpot.setStatus(ParkingSpot.Status.OCCUPIED);
        parkingSpotRepository.save(parkingSpot);

        return bookingRepository.save(booking);
    }
}
