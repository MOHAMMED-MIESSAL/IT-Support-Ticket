package com.projets.itsupportticket.dto;

import com.projets.itsupportticket.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


/**
 * DTO for updating the status of a ticket.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {
    private Status status;
    private UUID userId;
}
