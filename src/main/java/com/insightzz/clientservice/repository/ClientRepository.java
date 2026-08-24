package com.insightzz.clientservice.repository;

import com.insightzz.clientservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository
        extends JpaRepository<Client, Long> {

    boolean existsByClientNameAndClientLocation(
            String clientName,
            String clientLocation
    );

    boolean existsByClientName(String clientName);

    boolean existsByClientEmail(String clientEmail);

    Optional<Client> findByClientEmail(String clientEmail);

    @Query("""
        SELECT c
        FROM Client c
        WHERE c.id IN (
            SELECT MIN(c2.id)
            FROM Client c2
            GROUP BY c2.clientName
        )
        ORDER BY c.clientName
        """)
    List<Client> findDistinctClientNames();

    List<Client> findByClientName(
            String clientName
    );

    @Query("""
            SELECT c.clientLocation
            FROM Client c
            WHERE LOWER(c.clientName) = LOWER(:clientName)
            ORDER BY c.clientLocation
            """)
    List<String> findLocationsByClientName(
            String clientName
    );
}
