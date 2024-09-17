package com.app.community.controller;

import com.app.community.domain.agg.comment.CommentQuery.CommentInfo;
import com.app.community.domain.agg.comment.CommentQuery.ProfileComment;
import com.app.community.domain.agg.comment.CommentReadService;
import com.app.community.support.response.ApiResponse;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentQueryController {

    private final CommentReadService commentQueryService;

    @GetMapping("/{articleId}")
    ApiResponse<CursorResult<CommentInfo>> getCommentList(
            @PathVariable(name = "articleId") Long articleId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ){
        var comments = commentQueryService.getCommentList(articleId, size, cursor);
        var commentInfo = CursorResult.of(comments, comments.size(), CommentInfo::getCommentId);
        return ApiResponse.success(commentInfo);
    }

    @GetMapping("/profiles-answer")
    ApiResponse<CursorResult<ProfileComment>> getAnswerByMember(
            @AuthenticationPrincipal Long loginMemberId,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ) {
        var comments = commentQueryService.getAnswerByMember(loginMemberId, size, cursor);
        var profileComment = CursorResult.of(comments, comments.size(), ProfileComment::getCommentId);
        return ApiResponse.success(profileComment);
    }
}
