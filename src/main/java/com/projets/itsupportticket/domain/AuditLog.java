package com.projets.itsupportticket.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    private String actionType;  // "STATUS_UPDATE" ou "COMMENT_ADDED"

    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;

    private String oldValue;
    private String newValue;

    private LocalDateTime changeDate = LocalDateTime.now();

}
