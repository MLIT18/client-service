package com.insightzz.clientservice.service;

import com.insightzz.clientservice.dto.ClientCreateRequest;
import com.insightzz.clientservice.dto.ClientDropdownResponse;
import com.insightzz.clientservice.dto.ClientResponse;
import com.insightzz.clientservice.dto.ClientUpdateRequest;
import com.insightzz.clientservice.entity.Client;
import com.insightzz.clientservice.exception.DuplicateClientException;
import com.insightzz.clientservice.exception.ClientNotFoundException;
import com.insightzz.clientservice.repository.ClientRepository;
import com.insightzz.clientservice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl
        implements ClientService {

    private final ClientRepository clientRepository;


    // =========================================================
    // CREATE CLIENT
    // =========================================================

    @Override
    public ClientResponse createClient(
            ClientCreateRequest request) {

        if (clientRepository.existsByClientNameAndClientLocation(
                request.getClientName(),
                request.getClientLocation())) {

            throw new DuplicateClientException(
                    "Client with same name and location already exists"
            );
        }

        String currentUserName =
                SecurityUtil.getCurrentUsername();
        Client client = Client.builder()
                .clientName(request.getClientName())
                .clientLocation(
                        request.getClientLocation()
                )
                .clientSpocName(
                        request.getClientSpocName()
                )
                .clientSpocDesignation(request.getClientSpocDesignation())
                .clientEmail(
                        request.getClientEmail()
                )
//                .status(
//                        request.getStatus()
//                )
                .createdBy(currentUserName)
                .updatedBy(currentUserName)
                .build();


        Client savedClient =
                clientRepository.save(client);


        return mapToResponse(savedClient);
    }


    // =========================================================
    // GET CLIENT BY ID
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public ClientResponse getClientById(Long id) {

        Client client =
                clientRepository.findById(id)
                        .orElseThrow(() ->
                                new ClientNotFoundException(
                                        "Client not found with id: "
                                                + id
                                )
                        );

        return mapToResponse(client);
    }


    // =========================================================
    // GET ALL CLIENTS
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> getAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =========================================================
    // UPDATE CLIENT
    // =========================================================

    @Override
    public ClientResponse updateClient(
            Long id,
            ClientUpdateRequest request) {

        Client client =
                clientRepository.findById(id)
                        .orElseThrow(() ->
                                new ClientNotFoundException(
                                        "Client not found with id: " + id
                                )
                        );

        String currentUserName =
                SecurityUtil.getCurrentUsername();


        // -----------------------------------------------------
        // CLIENT NAME
        // -----------------------------------------------------

        if (request.getClientName() != null
                && !request.getClientName()
                .equals(client.getClientName())) {

            if (clientRepository.existsByClientName(
                    request.getClientName())) {

                throw new DuplicateClientException(
                        "Client name already exists"
                );
            }

            client.setClientName(
                    request.getClientName()
            );
        }


        // -----------------------------------------------------
        // CLIENT LOCATION
        // -----------------------------------------------------

        if (request.getClientLocation() != null) {

            client.setClientLocation(
                    request.getClientLocation()
            );
        }


        // -----------------------------------------------------
        // SPOC
        // -----------------------------------------------------

        if (request.getClientSpocName() != null) {

            client.setClientSpocName(
                    request.getClientSpocName()
            );
        }


        // -----------------------------------------------------
        // EMAIL
        // -----------------------------------------------------

        if (request.getClientEmail() != null
                && !request.getClientEmail()
                .equals(client.getClientEmail())) {

            if (clientRepository.existsByClientEmail(
                    request.getClientEmail())) {

                throw new DuplicateClientException(
                        "Client email already exists"
                );
            }

            client.setClientEmail(
                    request.getClientEmail()
            );
        }


        // -----------------------------------------------------
        // STATUS
        // -----------------------------------------------------

//        if (request.getStatus() != null) {
//
//            client.setStatus(
//                    request.getStatus()
//            );
//        }


        // -----------------------------------------------------
        // UPDATED BY
        // -----------------------------------------------------

        client.setUpdatedBy(currentUserName);


        Client updatedClient =
                clientRepository.save(client);

        return mapToResponse(updatedClient);
    }


    // =========================================================
    // DELETE CLIENT
    // =========================================================

    @Override
    public void deleteClient(Long id) {

        Client client =
                clientRepository.findById(id)
                        .orElseThrow(() ->
                                new ClientNotFoundException(
                                        "Client not found with id: "
                                                + id
                                )
                        );

        clientRepository.delete(client);
    }


    // =========================================================
    // ENTITY → RESPONSE
    // =========================================================

    private ClientResponse mapToResponse(
            Client client) {

        return ClientResponse.builder()
                .clientId(
                        client.getId()
                )
                .clientName(
                        client.getClientName()
                )
                .clientLocation(
                        client.getClientLocation()
                )
                .clientSpocName(
                        client.getClientSpocName()
                )
                .clientSpocDesignation(client.getClientSpocDesignation())
                .clientEmail(
                        client.getClientEmail()
                )
//                .status(
//                        client.getStatus()
//                )
                .createdAt(
                        client.getCreatedAt()
                )
                .updatedAt(
                        client.getUpdatedAt()
                )
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getLocationsByClientName(
            String clientName) {

        return clientRepository
                .findByClientName(clientName)
                .stream()
                .map(Client::getClientLocation)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDropdownResponse> getAllClientNames() {

        return clientRepository
                .findDistinctClientNames()
                .stream()
                .map(client ->
                        new ClientDropdownResponse(
                                client.getId(),
                                client.getClientName()
                        )
                )
                .toList();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<String> getLocationsByClientName(
//            String clientName) {
//
//        return clientRepository
//                .findLocationsByClientName(clientName);
//    }
}
