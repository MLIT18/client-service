package com.insightzz.clientservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUpdateRequest {

    @Size(
            max = 200,
            message = "Client name cannot exceed 200 characters"
    )
    private String clientName;

    @Size(
            max = 255,
            message = "Client location cannot exceed 255 characters"
    )
    private String clientLocation;

    @Size(
            max = 100,
            message = "Client SPOC name cannot exceed 100 characters"
    )
    private String clientSpocName;

    @Email(message = "Invalid client email")
    @Size(
            max = 254,
            message = "Client email cannot exceed 254 characters"
    )
    private String clientEmail;

    //private String status;
}
