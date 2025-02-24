package com.projets.itsupportticket.web.controller;


import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.dto.TicketCreateDto;
import com.projets.itsupportticket.dto.UpdateStatusRequest;
import com.projets.itsupportticket.enums.Status;
import com.projets.itsupportticket.mapper.TicketMapper;
import com.projets.itsupportticket.service.TicketService;
import com.projets.itsupportticket.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final UserService userService;


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

    // Method to update the status of a ticket to add log to database
    @PatchMapping("/status/{id}")
    public ResponseEntity<Ticket> updateTicketStatus(
            @PathVariable UUID id,
            @RequestBody UpdateStatusRequest updateStatusRequest) {

        User user = userService.findById(updateStatusRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // + The log is added to the database when the status is updated before the status is updated

        return ResponseEntity.ok(ticketService.updateStatus(id, updateStatusRequest.getStatus(), user));
    }

    // Method to get a ticket by status and id
    @GetMapping("/{id}/status")
    public ResponseEntity<Optional<Ticket>> findByStatusAndId(@RequestParam Status status, @PathVariable UUID id) {
        return ResponseEntity.status(200).body(ticketService.findByStatusAndId(status, id));
    }

    // Method to get a list of tickets by status
    @GetMapping("/status")
    public ResponseEntity<List<Ticket>> findByStatus(@RequestParam Status status) {
        return ResponseEntity.status(200).body(ticketService.findByStatus(status));
    }
}
