package com.example.airline.airline_booking_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dob;
    private String gender;
    private String passportNumber;
    private String passportExpiry;
}
