package com.app.community.controller;

import com.app.community.domain.agg.comment.CommentQuery.CommentInfo;
import com.app.community.domain.agg.comment.CommentQuery.ProfileComment;
import com.app.community.domain.agg.comment.CommentReadService;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentQueryController {

    private final CommentReadService commentQueryService;

    @GetMapping("/{articleId}")
    ResponseEntity<CursorResult<CommentInfo>> getCommentList(
            @PathVariable(name = "articleId") Long articleId,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ){
        List<CommentInfo> comments = commentQueryService.getCommentList(articleId, cursor);
        return ResponseEntity.ok().body(CursorResult.of(comments, comments.size(), CommentInfo::getCommentId));
    }

    @GetMapping("/profiles-answer")
    ResponseEntity<CursorResult<ProfileComment>> getAnswerByMember(
            @AuthenticationPrincipal Long loginMemberId,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ) {
        List<ProfileComment> comments = commentQueryService.getAnswerByMember(loginMemberId, cursor);
        return ResponseEntity.ok().body(CursorResult.of(comments, comments.size(), ProfileComment::getCommentId));
    }
}
