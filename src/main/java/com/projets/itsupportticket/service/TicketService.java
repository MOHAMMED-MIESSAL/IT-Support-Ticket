package com.projets.itsupportticket.service;

import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TicketService {
    Page<Ticket> findAll(Pageable pageable);
    Ticket create(Ticket ticket);
    Ticket update(UUID id, Ticket ticket);
    Optional<Ticket> findById(UUID id);
    void delete(UUID id);
    Ticket updateStatus(UUID id , Status newStatus, User changedBy);
}
