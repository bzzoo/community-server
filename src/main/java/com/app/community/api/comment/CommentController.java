package com.app.community.api.comment;

import com.app.community.domain.comment.CommentQueryService;
import com.app.community.domain.comment.CommentService;
import com.app.community.domain.comment.CommentSummary;
import com.app.community.support.response.CursorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentQueryService commentQueryService;

    @PostMapping("/{targetId}/new")
    public ResponseEntity<Void> create(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "targetId") Long targetId,
            @RequestBody CommentRequest.CreateRequest request
    ) {
        commentService.create(memberId, targetId, request.targetType(), request.content());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{targetId}")
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "targetId") Long targetId,
            @RequestBody CommentRequest.UpdateRequest request
    ) {
        commentService.update(memberId, targetId, request.content());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{targetId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "targetId") Long targetId
    ) {
        commentService.delete(memberId, targetId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<CursorResult<CommentSummary.CommentInfo>> getCommentList(
            @PathVariable(name = "articleId") Long articleId,
            @RequestParam(name = "c", required = false, defaultValue = "-1") Long cursor
    ){
        CursorResult<CommentSummary.CommentInfo> commentList = commentQueryService.getCommentList(articleId, cursor);
        return ResponseEntity.ok().body(commentList);
    }
}
