package com.example.airline.airline_booking_service.event_store.repository;

import com.example.airline.airline_booking_service.event_store.entity.SnapshotBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SnapshotBookingRepository extends JpaRepository<SnapshotBookingEntity, UUID> {


    @Query("""
            SELECT s FROM SnapshotBookingEntity s
            WHERE s.bookingId = :bookingId
            """)
    Optional<SnapshotBookingEntity> findByBookingId(UUID bookingId);
}
