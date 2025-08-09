package com.example.airline.airline_booking_service.domain.aggregate;


import com.example.airline.airline_booking_service.command.create.CreateBookingCommand;
import com.example.airline.airline_booking_service.domain.model.Passenger;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class BookingAggregate {
    private UUID id;
    private String referenceCode;
    private UUID customerId;
    private UUID flightId;
    private String status;
    private BigDecimal totalAmount;
    private List<Passenger> passengers;
    private Instant createdAt;
    private int version;

    public static BookingAggregate createFrom(CreateBookingCommand cmd, String refCode) {
        List<Passenger> passengers = cmd.getPassengers().stream()
                .map(p -> Passenger.builder()
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .email(p.getEmail())
                        .phone(p.getPhone())
                        .dob(LocalDate.parse(p.getDob()))
                        .gender(p.getGender())
                        .passportNumber(p.getPassportNumber())
                        .passportExpiry(LocalDate.parse(p.getPassportExpiry()))
                        .build()).collect(Collectors.toList());

        return BookingAggregate.builder()
                .id(UUID.randomUUID())
                .referenceCode(refCode)
                .customerId(cmd.getCustomerId())
                .flightId(cmd.getFlightId())
                .status("PENDING_PAYMENT")
                .totalAmount(cmd.getTotalAmount())
                .passengers(passengers)
                .createdAt(Instant.now())
                .version(1)
                .build();
    }
}
