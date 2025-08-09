package com.example.airline.airline_booking_service.domain.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Passenger {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String passportNumber;
    private LocalDate passportExpiry;
}
