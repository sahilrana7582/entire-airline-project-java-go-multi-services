package com.example.airline.airline_booking_service.command.create;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateBookingCommand {
    private final UUID customerId;
    private final UUID flightId;
    private final BigDecimal totalAmount;
    private final List<PassengerInfo> passengers;


    @Getter
    @AllArgsConstructor
    public static class PassengerInfo {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String phone;
        private final String dob;
        private final String gender;
        private final String passportNumber;
        private final String passportExpiry;
    }
}
