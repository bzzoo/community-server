package com.app.community.api.controller;

import com.app.community.api.support.response.ApiResponse;
import com.app.community.api.support.response.CursorResult;
import com.app.community.domain.query.CommentQueryService;
import com.app.community.domain.model.member.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.app.community.domain.query.CommentQuery.CommentDetails;
import static com.app.community.domain.query.CommentQuery.ProfileComment;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@RestController
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    @GetMapping("{commentId}")
    ApiResponse<CommentDetails> getComment(
            @PathVariable(name = "commentId") Long commentId
    ) {
        var comment = commentQueryService.getComment(commentId);
        return ApiResponse.success(comment);
    }

    @GetMapping()
    ApiResponse<CursorResult<CommentDetails>> getCommentsByArticleId(
            @RequestParam(name = "aid") Long articleId,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var comments = commentQueryService.getCommentsByArticleId(articleId, size + 1, cursor);
        var commentInfo = CursorResult.of(comments, size, CommentDetails::getId);
        return ApiResponse.success(commentInfo);
    }

    @GetMapping("/more-button")
    ApiResponse<CursorResult<CommentDetails>> getCommentsWithDepth(
            @RequestParam(name = "aid") Long articleId,
            @RequestParam(name = "pid", required = false) Long parentId,
            @RequestParam(name = "dp", required = false, defaultValue = "1") int depth,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var comments = commentQueryService.getCommentsWithDepth(articleId, parentId, size + 1, cursor, depth);
        var commentInfo = CursorResult.of(comments, size, CommentDetails::getId);
        return ApiResponse.success(commentInfo);
    }

    @GetMapping("/profiles-answer")
    ApiResponse<CursorResult<ProfileComment>> getAnswerByMember(
            @AuthenticationPrincipal LoginMember member,
            @RequestParam(name = "sz", required = false, defaultValue = "20") int size,
            @RequestParam(name = "cr", required = false, defaultValue = "-1") Long cursor
    ) {
        var comments = commentQueryService.getAnswerByMember(member.memberId(), size, cursor);
        var profileComment = CursorResult.of(comments, comments.size(), ProfileComment::getCommentId);
        return ApiResponse.success(profileComment);
    }
}