package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.RideRequest;
import com.cabbookingsystem.entity.Ride;
import com.cabbookingsystem.entity.User;
import com.cabbookingsystem.entity.Driver;
import com.cabbookingsystem.enums.RideStatus;
import com.cabbookingsystem.repository.RideRepository;
import com.cabbookingsystem.repository.UserRepository;
import com.cabbookingsystem.repository.DriverRepository;
import com.cabbookingsystem.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    /** BOOK A RIDE **/
    @Override
    public String bookRide(RideRequest request) {
        // Validate User
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find first available driver
        Driver driver = driverRepository.findFirstByStatus("AVAILABLE")
                .orElseThrow(() -> new RuntimeException("No available drivers at the moment"));

        // Mark driver as unavailable
        driver.setStatus("UNAVAILABLE");
        driverRepository.save(driver);

        // Calculate fare (₹10 per km as an example)
        double fare = request.getDistance() * 10;

        // Create and save Ride
        Ride ride = new Ride(null, user, driver, request.getPickupLocation(),
                request.getDropoffLocation(), fare, RideStatus.BOOKED.name());
        rideRepository.save(ride);

        return "Ride booked successfully!";
    }

    /** UPDATE RIDE STATUS **/
    @Override
    public String updateRideStatus(Long rideId, String status) {
        // Find Ride
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Update Ride Status
        ride.setStatus(status);

        // Mark driver as available if ride is completed
        if (status.equalsIgnoreCase(RideStatus.COMPLETED.name())) {
            Driver driver = ride.getDriver();
            driver.setStatus("AVAILABLE");
            driverRepository.save(driver);
        }

        rideRepository.save(ride);
        return "Ride status updated!";
    }

    /** GET USER RIDE HISTORY **/
    @Override
    public List<Ride> getUserRideHistory(Long userId) {
        // Validate User
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch Ride History
        return rideRepository.findByUser_UserID(userId);
    }
}