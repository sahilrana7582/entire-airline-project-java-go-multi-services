package com.example.airline.airline_booking_service.events;

import com.example.airline.airline_booking_service.domain.model.Passenger;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class BookingCreatedEvent {
    private UUID eventId;
    private Instant occurredAt;
    private UUID bookingId;
    private String referenceCode;
    private UUID customerId;
    private UUID flightId;
    private String status;
    private BigDecimal totalAmount;
    private List<Passenger> passengers;
}