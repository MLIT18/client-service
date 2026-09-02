package com.insightzz.clientservice.service;

import com.insightzz.clientservice.dto.ClientCreateRequest;
import com.insightzz.clientservice.dto.ClientDropdownResponse;
import com.insightzz.clientservice.dto.ClientResponse;
import com.insightzz.clientservice.dto.ClientUpdateRequest;

import java.util.List;

public interface ClientService {

    ClientResponse createClient(
            ClientCreateRequest request
    );

    ClientResponse getClientById(
            Long clientId
    );

    List<ClientResponse> getAllClients();

    ClientResponse updateClient(
            Long id,
            ClientUpdateRequest request
    );

    void deleteClient(
            Long id
    );

    // List<String> getLocationsByClientName(String clientName);

    // =====================================================
    // CLIENT DROPDOWN
    // =====================================================

    //List<String> getAllClientNames();

    // =====================================================
    // LOCATION DROPDOWN
    // =====================================================

    List<String> getLocationsByClientName(
            String clientName
    );

    List<ClientDropdownResponse> getAllClientNames();
}
