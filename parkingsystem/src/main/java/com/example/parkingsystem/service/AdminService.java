
package com.example.parkingsystem.service;

import com.example.parkingsystem.model.ParkingSpot;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.Repository.ParkingSpotRepository;
import com.example.parkingsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private UserRepository userRepository;

    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.save(parkingSpot);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
