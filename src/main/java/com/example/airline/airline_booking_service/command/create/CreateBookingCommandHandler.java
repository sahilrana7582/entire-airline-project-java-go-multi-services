package com.example.airline.airline_booking_service.command.create;


import com.example.airline.airline_booking_service.service.CreateBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBookingCommandHandler {

    private final CreateBookingService service;

    public CreateBookingResponse handle(CreateBookingCommand cmd) {
        var bookingAggregate = service.create(cmd);
        return new CreateBookingResponse(bookingAggregate.getId(), bookingAggregate.getReferenceCode());
    }

    public record CreateBookingResponse(java.util.UUID bookingId, String referenceCode) {}
}
