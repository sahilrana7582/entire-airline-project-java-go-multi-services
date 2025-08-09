package com.example.airline.airline_booking_service.event_store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "snapshot_booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapshotBookingEntity {

    @Id
    @Column(name = "snapshot_id", nullable = false)
    private UUID snapshotId;

    @Column(name = "booking_id", nullable = false, unique = true)  // unique to allow findByBookingId fast
    private UUID bookingId;

    @JdbcTypeCode(SqlTypes.JSON)  // Hibernate 6+ annotation to map JSONB properly
    @Column(name = "snapshot_payload", columnDefinition = "jsonb", nullable = false)
    private String snapshotPayload;

    @Column(name = "snapshot_version", nullable = false)
    private int snapshotVersion;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
