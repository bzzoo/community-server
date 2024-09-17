package com.app.community.controller;

import com.app.community.domain.agg.comment.CommentService;
import com.app.community.domain.agg.member.LoginMember;

import com.app.community.controller.request.CommentRequests.*;
import com.app.community.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    ApiResponse<Void> create(
            @AuthenticationPrincipal LoginMember member,
            @RequestBody NewCommentRequest request
    ) {
        commentService.create(member, request.articleId(), request.toTarget(), request.content());
        return ApiResponse.success();
    }

    @PostMapping("/{commentId}")
    ApiResponse<Void> update(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "commentId") Long commentId,
            @RequestBody UpdateRequest request
    ) {
        commentService.update(member, commentId, request.content());
        return ApiResponse.success();
    }

    @PutMapping("/{commentId}")
    ApiResponse<Void> delete(
            @AuthenticationPrincipal LoginMember member,
            @PathVariable(name = "commentId") Long commentId
    ) {
        commentService.delete(member, commentId);
        return ApiResponse.success();
    }

}
