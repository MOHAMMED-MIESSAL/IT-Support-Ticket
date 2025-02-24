package com.projets.itsupportticket.service.Implementations;

import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.repository.CommentRepository;
import com.projets.itsupportticket.service.CommentService;
import com.projets.itsupportticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * CommentImplementation
 */

@Service
@RequiredArgsConstructor
public class CommentImplementation implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketService ticketService;

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment create(UUID ticketId, User author, String content) {
        Ticket ticket = ticketService.findById(ticketId)
                .orElseThrow(() -> new CustomValidationException("Ticket with id : " + ticketId + " not found"));

        if (content == null || content.trim().isEmpty()) {
            throw new CustomValidationException("Comment content cannot be empty.");
        }

        Comment comment = new Comment();
        comment.setTicket(ticket);
        comment.setAuthor(author);
        comment.setCommentText(content);

        // Log the comment addition is automatically done by the AuditLogAspect
        // auditLogService.logCommentAddition(comment);

        return commentRepository.save(comment);
    }


}
