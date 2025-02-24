package com.projets.itsupportticket.service.Implementations;

import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.enums.Status;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.repository.TicketRepository;
import com.projets.itsupportticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        ticket.setCreationDate(LocalDateTime.now()); // Set the creation date automatically
        ticket.setStatus(Status.NEW); // Set the status to NEW  by default
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(UUID id) {
        if (ticketRepository.findById(id).isEmpty()) {
            throw new CustomValidationException("Ticket with id : " + id + " not found");
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
            throw new CustomValidationException("Ticket with id : " + id + " not found");
        }
        ticket.setId(id);
        ticket.setCreationDate(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateStatus(UUID id, Status newStatus, User changedBy) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomValidationException("Ticket not found"));

        ticket.setStatus(newStatus);

        // Log the status change is automatically done by the AuditLogAspect
        // auditLogService.logStatusChange(ticket, oldStatus, newStatus, changedBy);

        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findByStatusAndId(Status status, UUID id) {
        return Optional.ofNullable(ticketRepository.findByStatusAndId(status, id)
                .orElseThrow(() -> new CustomValidationException("Ticket not found")));
    }

    @Override
    public List<Ticket> findByStatus(Status status) {
        return ticketRepository.findByStatus(status);
    }
}
