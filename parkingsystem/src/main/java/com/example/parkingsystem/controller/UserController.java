package com.example.parkingsystem.controller;



import com.example.parkingsystem.model.ParkingSpot;


import com.example.parkingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/parking/spots")
    @PreAuthorize("hasRole('USER')")
    public List<ParkingSpot> getAvailableSpots() {
        return userService.getAvailableSpots();
    }


}
