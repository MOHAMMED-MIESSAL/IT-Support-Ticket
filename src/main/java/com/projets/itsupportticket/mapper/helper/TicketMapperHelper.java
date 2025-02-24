package com.projets.itsupportticket.mapper.helper;


import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TicketMapperHelper {

    private final TicketRepository ticketRepository;

    public Ticket toTicket(UUID ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new CustomValidationException("Ticket with id : " + ticketId + " not found"));
    }
}
