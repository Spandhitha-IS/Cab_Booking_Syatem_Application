package com.cabbookingsystem.dto;

import lombok.Data;

@Data
public class RideRequest {
    private Long userId;
    private String pickupLocation;
    private String dropoffLocation;
    private double distance; // Used for fare calculation
}
