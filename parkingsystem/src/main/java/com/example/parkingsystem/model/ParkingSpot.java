package com.example.parkingsystem.model;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private String location;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        AVAILABLE, OCCUPIED
    }

    // Getters and setters
}
