package com.example.airline.airline_booking_service.controller;

import com.example.airline.airline_booking_service.command.create.CreateBookingCommand;
import com.example.airline.airline_booking_service.command.create.CreateBookingCommandHandler;
import com.example.airline.airline_booking_service.dto.CreateBookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final CreateBookingCommandHandler handler;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request) {
        List<CreateBookingCommand.PassengerInfo> passengers = request.getPassengers().stream()
                .map(p -> new CreateBookingCommand.PassengerInfo(
                        p.getFirstName(), p.getLastName(), p.getEmail(),
                        p.getPhone(), p.getDob(), p.getGender(),
                        p.getPassportNumber(), p.getPassportExpiry()
                ))
                .collect(Collectors.toList());

        CreateBookingCommand command = new CreateBookingCommand(
                request.getCustomerId(),
                request.getFlightId(),
                request.getTotalAmount(),
                passengers
        );

        var response = handler.handle(command);
        return ResponseEntity.status(201).body(response);
    }
}