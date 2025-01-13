package com.example.parkingsystem.service;

import com.example.parkingsystem.model.ParkingSpot;
import com.example.parkingsystem.Repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpot> getAllAvailableSpots() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.save(parkingSpot);
    }

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll();
    }
    
    public ParkingSpot updateSpotStatus(Long spotId, String status) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));
    
        ParkingSpot.Status newStatus = ParkingSpot.Status.valueOf(status);
        parkingSpot.setStatus(newStatus);
        
        return parkingSpotRepository.save(parkingSpot);
    }

    public void createSpot(ParkingSpot parkingSpot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSpot'");
    }
    
    
}
