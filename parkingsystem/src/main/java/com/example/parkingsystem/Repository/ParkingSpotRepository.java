package com.example.parkingsystem.Repository;

import com.example.parkingsystem.model.ParkingSpot;

import ch.qos.logback.core.status.Status;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByStatus(Status status);
}
