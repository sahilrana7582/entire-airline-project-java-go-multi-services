package com.example.airline.airline_booking_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    private UUID customerId;
    private UUID flightId;
    private BigDecimal totalAmount;
    private List<PassengerDto> passengers;
}