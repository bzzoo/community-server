package com.app.community.controller;

import com.app.community.domain.agg.comment.CommentService;
import com.app.community.domain.agg.member.LoginMember;

import com.app.community.controller.request.CommentRequests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    ResponseEntity<Void> create(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody NewCommentRequest request
    ) {
        commentService.create(member, request.articleId(), request.toTarget(), request.content());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{commentId}")
    ResponseEntity<Void> update(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "commentId") Long commentId,
            @RequestBody UpdateRequest request
    ) {
        commentService.update(member, commentId, request.content());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentId}")
    ResponseEntity<Void> delete(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "commentId") Long commentId
    ) {
        commentService.delete(member, commentId);
        return ResponseEntity.ok().build();
    }

}
