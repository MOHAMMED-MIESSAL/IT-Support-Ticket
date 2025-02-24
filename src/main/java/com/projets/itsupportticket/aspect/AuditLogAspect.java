package com.projets.itsupportticket.aspect;

import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.enums.Status;
import com.projets.itsupportticket.repository.TicketRepository;
import com.projets.itsupportticket.service.AuditLogService;
import com.projets.itsupportticket.service.Implementations.AuditLogServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditLogAspect {

//    private final AuditLogServiceImplementation auditLogServiceImplementation;
    private final AuditLogService auditLogService;
    private final TicketRepository ticketRepository;

    // Inspection of the status change of a ticket
    @Before("execution(* com.projets.itsupportticket.service.TicketService.updateStatus(..))")
    public void logTicketStatusChange(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        UUID ticketId = (UUID) args[0];
        Status newStatus = (Status) args[1];
        User changedBy = (User) args[2];

        // Get the ticket from the database to get the old status
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        Status oldStatus = ticket.getStatus();

        auditLogService.logStatusChange(ticket, oldStatus, newStatus, changedBy);
    }


    // Inspection of the addition of a comment
    @AfterReturning(pointcut = "execution(* com.projets.itsupportticket.service.CommentService.create(..))", returning = "comment")
    public void logCommentAddition(Comment comment) {
        auditLogService.logCommentAddition(comment);
    }

}
