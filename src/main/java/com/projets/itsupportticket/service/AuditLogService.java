package com.projets.itsupportticket.service;

import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.domain.Ticket;
import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.enums.Status;

public interface AuditLogService {

    void logStatusChange(Ticket ticket, Status oldStatus, Status newStatus, User changedBy);

    void logCommentAddition(Comment comment);
}
