package com.projets.itsupportticket.web.controller;


import com.projets.itsupportticket.domain.Comment;
import com.projets.itsupportticket.dto.CommentCreateDto;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.mapper.CommentMapper;
import com.projets.itsupportticket.service.CommentService;
import com.projets.itsupportticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {


    private final UserService userService;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<Page<Comment>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(200).body(commentService.findAll(pageable));
    }


    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentCreateDto commentCreateDto) {
        Comment comment = commentMapper.toEntity(commentCreateDto);
        return ResponseEntity.status(201).body(commentService.create(UUID.fromString(commentCreateDto.getTicketId()),
                userService.findById(UUID.fromString(commentCreateDto.getUserId()))
                        .orElseThrow(() -> new CustomValidationException("User not found")),
                comment.getCommentText()));
    }

}
