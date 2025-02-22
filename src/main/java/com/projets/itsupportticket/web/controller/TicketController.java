package com.projets.itsupportticket.web.controller;


import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.dto.TicketCreateDto;
import com.projets.itsupportticket.mapper.TicketMapper;
import com.projets.itsupportticket.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;


    @GetMapping
    public ResponseEntity<Page<Ticket>> findAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(200).body(ticketService.findAll(pageable));
    }


    @PostMapping
    public ResponseEntity<Ticket> save(@Valid @RequestBody TicketCreateDto ticketCreateDto) {
        Ticket ticket = ticketMapper.toEntity(ticketCreateDto);
        return ResponseEntity.status(201).body(ticketService.create(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable UUID id, @Valid @RequestBody TicketCreateDto ticketCreateDto) {
        Ticket ticket = ticketMapper.toEntity(ticketCreateDto);
        return ResponseEntity.status(200).body(ticketService.update(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ticket>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(ticketService.findById(id));
    }
}
