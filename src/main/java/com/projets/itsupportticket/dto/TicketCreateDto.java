package com.projets.itsupportticket.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for creating a new Ticket.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreateDto {

    @NotBlank(message = "Title must not be null")
    private String title;

    @NotBlank(message = "Description must not be null")
    private String description;

    @NotNull(message = "Priority must not be null")
    @Enumerated(EnumType.STRING)
    private String priority;

    // @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    private String status;

    @NotNull(message = "Category must not be null")
    @Enumerated(EnumType.STRING)
    private String category;

    @NotNull(message = "User ID must not be null")
    private UUID userId;

}
