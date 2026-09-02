package com.insightzz.clientservice.controller;

import com.insightzz.clientservice.dto.ClientCreateRequest;
import com.insightzz.clientservice.dto.ClientDropdownResponse;
import com.insightzz.clientservice.dto.ClientResponse;
import com.insightzz.clientservice.dto.ClientUpdateRequest;
import com.insightzz.clientservice.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    // =========================================================
    // CREATE
    // =========================================================

    @PreAuthorize("hasAuthority('CLIENT_CREATE')")
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(
            @Valid @RequestBody ClientCreateRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        clientService.createClient(request)
                );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @PreAuthorize("hasAuthority('CLIENT_READ')")
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {

        return ResponseEntity.ok(
                clientService.getAllClients()
        );
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @PreAuthorize("hasAuthority('CLIENT_READ')")
    @GetMapping("/id/{clientId}")
    public ResponseEntity<ClientResponse> getClientById(
            @PathVariable Long clientId) {

        return ResponseEntity.ok(
                clientService.getClientById(clientId)
        );
    }


    // =========================================================
    // UPDATE
    // =========================================================

    @PreAuthorize("hasAuthority('CLIENT_UPDATE')")
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientUpdateRequest request) {

        return ResponseEntity.ok(
                clientService.updateClient(
                        id,
                        request
                )
        );
    }


    // =========================================================
    // DELETE
    // =========================================================

    @PreAuthorize("hasAuthority('CLIENT_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable Long id) {

        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }

    // =====================================================
    // GET CLIENT NAMES
    // =====================================================

    @GetMapping("/names")
    public ResponseEntity<List<ClientDropdownResponse>>
    getClientNames() {

        return ResponseEntity.ok(
                clientService.getAllClientNames()
        );
    }

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getLocationsByClientName(
            @RequestParam String clientName) {

        return ResponseEntity.ok(
                clientService.getLocationsByClientName(
                        clientName
                )
        );
    }


}
