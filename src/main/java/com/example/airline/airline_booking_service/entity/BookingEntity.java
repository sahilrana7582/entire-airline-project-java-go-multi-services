package com.example.airline.airline_booking_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reference_code", nullable = false, unique = true, length = 12)
    private String referenceCode;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "flight_id", nullable = false)
    private UUID flightId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "ticket")
    private UUID ticket;


    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PassengerEntity> passengers;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

}
