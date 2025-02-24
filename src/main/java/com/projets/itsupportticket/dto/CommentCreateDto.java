package com.projets.itsupportticket.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating a new Comment.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDto {

        @NotBlank(message = "Comment text is required")
        private String commentText;

        @NotNull(message = "Ticket ID is required")
        private String ticketId;

        @NotNull(message = "User ID is required")
        private String userId;
}
