package com.example.parkingsystem.controller;

import com.example.parkingsystem.model.ParkingSpot;
import com.example.parkingsystem.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    // Get All Parking Spots
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/spots")
    public List<ParkingSpot> getAllParkingSpots() {
        return parkingService.getAllParkingSpots();
    }

    // Update Parking Spot Status
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{spotId}")
    public ResponseEntity<ParkingSpot> updateSpotStatus(@PathVariable Long spotId, @RequestBody String status) {
        try {
            ParkingSpot updatedSpot = parkingService.updateSpotStatus(spotId, status);
            return new ResponseEntity<>(updatedSpot, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
