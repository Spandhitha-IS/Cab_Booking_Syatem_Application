package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.RideRequest;
import com.cabbookingsystem.entity.Ride;
import java.util.List;

public interface RideService {
    String bookRide(RideRequest request);
    String updateRideStatus(Long rideId, String status);
    List<Ride> getUserRideHistory(Long userId);
}
