package com.cabbookingsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabbookingsystem.dto.RideRequest;
import com.cabbookingsystem.entity.Ride;
import com.cabbookingsystem.service.RideService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    /** BOOK A RIDE **/
    @PostMapping("/book")
    public ResponseEntity<String> bookRide(@RequestBody RideRequest request) {
        String response = rideService.bookRide(request);
        return ResponseEntity.ok(response);
    }

    /** UPDATE RIDE STATUS **/
    @PutMapping("/status/{rideId}")
    public ResponseEntity<String> updateRideStatus(@PathVariable Long rideId, @RequestParam String status) {
        String response = rideService.updateRideStatus(rideId, status);
        return ResponseEntity.ok(response);
    }

    /** GET USER RIDE HISTORY **/
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ride>> getUserRideHistory(@PathVariable Long userId) {
        List<Ride> rides = rideService.getUserRideHistory(userId);
        return ResponseEntity.ok(rides);
    }
}
