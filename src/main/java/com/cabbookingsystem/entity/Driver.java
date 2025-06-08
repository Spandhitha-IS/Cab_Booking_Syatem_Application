package com.cabbookingsystem.entity;

import java.util.List;

import com.cabbookingsystem.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverID;

    private String name;
    
    @Column(unique = true, nullable = false)
    private String phone;
    private String licenseNumber;
    private String vehicleDetails;
    private String status; 
    private String passwordHash; // Add this field

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Ride> rides;
}