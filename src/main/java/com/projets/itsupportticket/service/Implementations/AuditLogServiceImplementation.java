package com.projets.itsupportticket.service.Implementations;

import com.projets.itsupportticket.domain.AuditLog;
import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.enums.Status;
import com.projets.itsupportticket.repository.AuditLogRepository;
import com.projets.itsupportticket.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImplementation implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    // Store the audit log for a comment addition
    @Override
    public void logCommentAddition(Comment comment) {
        AuditLog log = new AuditLog();
        log.setTicket(comment.getTicket());
        log.setChangedBy(comment.getAuthor());
        log.setActionType("COMMENT_ADDED");
        log.setOldValue(null); // No old value for a new comment
        log.setNewValue(comment.getCommentText());
        log.setChangeDate(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    // Store the audit log for a status change
    @Override
    public void logStatusChange(Ticket ticket, Status oldStatus, Status newStatus, User changedBy) {
        AuditLog log = new AuditLog();
        log.setTicket(ticket);
        log.setChangedBy(changedBy);
        log.setActionType("STATUS_UPDATE");
        log.setOldValue(oldStatus.name());
        log.setNewValue(newStatus.name());
        log.setChangeDate(LocalDateTime.now());
        auditLogRepository.save(log);
    }

}
