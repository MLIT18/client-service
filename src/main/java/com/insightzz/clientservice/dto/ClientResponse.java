package com.insightzz.clientservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ClientResponse {

    private Long id;

    private String clientName;

    private String clientLocation;

    private String clientSpocName;

    private String clientSpocDesignation;

    private String clientEmail;

    //private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
