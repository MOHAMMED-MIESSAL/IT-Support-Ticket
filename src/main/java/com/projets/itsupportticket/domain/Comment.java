package com.projets.itsupportticket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String commentText;

    private LocalDateTime createdDate = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author; // User who create the comment
}