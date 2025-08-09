package com.example.airline.airline_booking_service.service;


import com.example.airline.airline_booking_service.command.create.CreateBookingCommand;
import com.example.airline.airline_booking_service.domain.aggregate.BookingAggregate;
import com.example.airline.airline_booking_service.domain.aggregate.BookingAggregateRepository;
import com.example.airline.airline_booking_service.events.BookingCreatedEvent;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBookingService {


    private final BookingAggregateRepository aggregateRepository;

    @Transactional
    public BookingAggregate create(CreateBookingCommand command) {
        if (command.getPassengers() == null || command.getPassengers().isEmpty()) {
            throw new ValidationException("Passengers required");
        }
        if (command.getTotalAmount() == null) {
            throw new ValidationException("totalAmount required");
        }

        String pnr = "XYZ87Y";

        BookingAggregate aggregate = BookingAggregate.createFrom(command, pnr);

        BookingCreatedEvent event = BookingCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(Instant.now())
                .bookingId(aggregate.getId())
                .referenceCode(aggregate.getReferenceCode())
                .customerId(aggregate.getCustomerId())
                .flightId(aggregate.getFlightId())
                .status(aggregate.getStatus())
                .totalAmount(aggregate.getTotalAmount())
                .passengers(aggregate.getPassengers())
                .build();

        return aggregateRepository.save(aggregate, event);
    }
}
