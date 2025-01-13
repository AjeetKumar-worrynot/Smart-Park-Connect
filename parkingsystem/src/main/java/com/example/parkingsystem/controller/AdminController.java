package com.example.parkingsystem.controller;

import com.example.parkingsystem.model.ParkingSpot;
import com.example.parkingsystem.model.User;
import com.example.parkingsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create-spot")
    @PreAuthorize("hasRole('ADMIN')")
    public ParkingSpot createSpot(@RequestBody ParkingSpot parkingSpot) {
        return adminService.createParkingSpot(parkingSpot);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }
}
