package com.cabbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabbookingsystem.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByUser_UserID(Long userId); // Retrieves ride history for a user
}
