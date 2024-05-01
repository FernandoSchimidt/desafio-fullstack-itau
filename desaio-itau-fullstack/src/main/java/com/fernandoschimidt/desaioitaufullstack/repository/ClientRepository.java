package com.fernandoschimidt.desaioitaufullstack.repository;

import com.fernandoschimidt.desaioitaufullstack.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT SUM(c.participation) FROM Client c")
    Double sumParticipation();
}
