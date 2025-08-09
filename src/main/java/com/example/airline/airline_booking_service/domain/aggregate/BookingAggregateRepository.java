package com.example.airline.airline_booking_service.domain.aggregate;


import com.example.airline.airline_booking_service.event_store.entity.EventStoreBookingEntity;
import com.example.airline.airline_booking_service.event_store.entity.SnapshotBookingEntity;
import com.example.airline.airline_booking_service.event_store.repository.EventStoreBookingRepository;
import com.example.airline.airline_booking_service.event_store.repository.SnapshotBookingRepository;
import com.example.airline.airline_booking_service.events.BookingCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BookingAggregateRepository {
    private final EventStoreBookingRepository eventStoreRepository;
    private final SnapshotBookingRepository snapshotRepository;
    private final ObjectMapper objectMapper;


    @Transactional
    public BookingAggregate save(BookingAggregate aggregate, BookingCreatedEvent event) {
        try {
            String eventString = objectMapper.writeValueAsString(event);

            EventStoreBookingEntity eventStoreBookingEntity = EventStoreBookingEntity.builder()
                    .eventId(event.getEventId())
                    .bookingId(event.getBookingId())
                    .eventType("BookingCreatedEvent")
                    .eventPayload(eventString)
                    .createdAt(event.getOccurredAt())
                    .eventVersion(aggregate.getVersion())
                    .build();

            eventStoreRepository.save(eventStoreBookingEntity);
            String snapshotjson = objectMapper.writeValueAsString(aggregate);

            Optional<SnapshotBookingEntity> optionalSnapShot = snapshotRepository.findByBookingId(event.getBookingId());
            SnapshotBookingEntity snapshotBookingEntity = optionalSnapShot.orElseGet(() -> SnapshotBookingEntity.builder()
                    .snapshotId(UUID.randomUUID())
                    .bookingId(event.getBookingId())
                    .createdAt(Instant.now())
                    .build());

            snapshotBookingEntity.setSnapshotPayload(snapshotjson);
            snapshotBookingEntity.setSnapshotVersion(aggregate.getVersion());

            snapshotRepository.save(snapshotBookingEntity);

            return aggregate;
        } catch (Exception e) {
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("========================================");

            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("========================================");
            System.out.println("========================================");
            throw new RuntimeException(e);
        }
    }

}
