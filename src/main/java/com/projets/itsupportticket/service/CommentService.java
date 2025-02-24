package com.projets.itsupportticket.service;

import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentService {

    Page<Comment> findAll(Pageable pageable);

    Comment create(UUID ticketId, User author, String comment);
}
