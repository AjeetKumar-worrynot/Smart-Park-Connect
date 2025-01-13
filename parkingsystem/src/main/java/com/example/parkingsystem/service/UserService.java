package com.example.parkingsystem.service;


import com.example.parkingsystem.model.ParkingSpot;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.Repository.ParkingSpotRepository;
import com.example.parkingsystem.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpotRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}



