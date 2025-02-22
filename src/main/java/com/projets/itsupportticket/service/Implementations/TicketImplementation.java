package com.projets.itsupportticket.service.Implementations;

import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.repository.TicketRepository;
import com.projets.itsupportticket.service.TicketService;
import com.projets.itsupportticket.service.UserService;
import liquibase.exception.CustomChangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


/**
 * TicketImplementation
 */

@Service
@RequiredArgsConstructor
public class TicketImplementation implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Ticket create(Ticket ticket) {
        ticket.setCreationDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(UUID id) {
        if (ticketRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ticket with id : " + id + " not found");
        }
        ticketRepository.deleteById(id);
    }


    @Override
    public Optional<Ticket> findById(UUID id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket update(UUID id, Ticket ticket) {
        if (ticketRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ticket with id : " + id + " not found");
        }
        ticket.setId(id);
        ticket.setCreationDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }
}
