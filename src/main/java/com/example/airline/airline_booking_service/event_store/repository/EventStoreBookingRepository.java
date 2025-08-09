package com.example.airline.airline_booking_service.event_store.repository;

import com.example.airline.airline_booking_service.event_store.entity.EventStoreBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface EventStoreBookingRepository extends JpaRepository<EventStoreBookingEntity, UUID> {


    @Query("""
            SELECT e FROM EventStoreBookingEntity e
            Where e.bookingId = :bookingId
            ORDER BY e.eventVersion ASC
            """)
    List<EventStoreBookingEntity> findByBookingIdOrderByVersionAsc(UUID bookingId);


    @Query("""
            SELECT COUNT(e) FROM EventStoreBookingEntity e
            Where e.bookingId = :bookingId
            """)
    int countByBookingId(UUID bookingId);


}
