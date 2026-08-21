package com.insightzz.clientservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "client_name",
            nullable = false,
            length = 200
    )
    private String clientName;

    @Column(
            name = "client_location",
            nullable = false,
            length = 255
    )
    private String clientLocation;

    @Column(
            name = "client_spoc_name",
            nullable = false,
            length = 100
    )
    private String clientSpocName;

    @Column(
            name = "client_spoc_designation",
            nullable = false,
            length = 100
    )
    private String clientSpocDesignation;

    @Column(
            name = "client_email",
            nullable = false,
            length = 254
    )
    private String clientEmail;

//    @Column(
//            name = "status",
//            nullable = false,
//            length = 20
//    )
//    private String status;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        LocalDateTime now =
                LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }

    @Column(
            name = "created_by",
            nullable = false
    )
    private String createdBy;

    @Column(
            name = "updated_by"
    )
    private String updatedBy;
}
