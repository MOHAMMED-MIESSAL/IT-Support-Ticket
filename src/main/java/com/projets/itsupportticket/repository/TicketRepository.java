package com.projets.itsupportticket.repository;

import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    Optional<Ticket> findByStatusAndId(Status status, UUID id);
    List<Ticket> findByStatus(Status status);
}
