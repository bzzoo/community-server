package com.app.community.api.comment;

import com.app.community.domain.comment.CommentQueryService;
import com.app.community.domain.comment.CommentSummary;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentQueryController {

    private final CommentQueryService commentQueryService;

    @GetMapping("/{articleId}")
    public ResponseEntity<CursorResult<CommentSummary.CommentInfo>> getCommentList(
            @PathVariable(name = "articleId") Long articleId,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ){
        CursorResult<CommentSummary.CommentInfo> commentList = commentQueryService.getCommentList(articleId, cursor);
        return ResponseEntity.ok().body(commentList);
    }

    @GetMapping("/profiles-answer")
    public ResponseEntity<CursorResult<CommentSummary.ProfileComment>> getAnswerByMember(
            @AuthenticationPrincipal Long loginMemberId,
            @RequestParam(name = "s", required = false, defaultValue = "20") int size,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ) {
        CursorResult<CommentSummary.ProfileComment> answerByMember = commentQueryService.getAnswerByMember(loginMemberId, cursor);
        return ResponseEntity.ok().body(answerByMember);
    }
}
