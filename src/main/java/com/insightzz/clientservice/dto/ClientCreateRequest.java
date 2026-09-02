package com.insightzz.clientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreateRequest {

    @NotBlank(message = "Client name is required")
    @Size(
            max = 200,
            message = "Client name cannot exceed 200 characters"
    )
    @Pattern(
            regexp = "^[A-Za-z_]+$",
            message = "Client name can contain only alphabets and underscore (_), no spaces allowed"
    )
    private String clientName;

    @NotBlank(message = "Client location is required")
    @Size(
            max = 255,
            message = "Client location cannot exceed 255 characters"
    )
    @Pattern(
            regexp = "^[A-Za-z_]+$",
            message = "Client Location name can contain only alphabets and underscore (_), no spaces allowed"
    )
    private String clientLocation;

    @NotBlank(message = "Client State is required")
    @Size(
            max = 255,
            message = "Client State cannot exceed 255 characters"
    )
    @Pattern(
            regexp = "^[A-Za-z_]+$",
            message = "Client State name can contain only alphabets and underscore (_), no spaces allowed"
    )
    private String clientState;

    //@NotBlank(message = "Client SPOC name is required")
    @Size(
            max = 100,
            message = "Client SPOC name cannot exceed 100 characters"
    )

    private String clientSpocName;

    @NotBlank(message = "Client email is required")
    @Email(message = "Invalid client email")
    @Size(
            max = 254,
            message = "Client email cannot exceed 254 characters"
    )
    private String clientEmail;

    @Size(
            max = 10,
            message = "Client Mob No cannot exceed 10 characters"
    )

    private String clientMobNo;



  //  private String status;
}
