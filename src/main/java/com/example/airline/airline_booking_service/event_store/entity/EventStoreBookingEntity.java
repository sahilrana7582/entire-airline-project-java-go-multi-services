package com.example.airline.airline_booking_service.event_store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "event_store_booking")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventStoreBookingEntity {

    @Id
    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @Column(name = "booking_id", nullable = false)
    private UUID bookingId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "event_payload", columnDefinition = "jsonb", nullable = false)
    private String eventPayload;


    @Column(name = "event_version", nullable = false)
    private int eventVersion;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
