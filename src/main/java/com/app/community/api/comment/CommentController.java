package com.app.community.api.comment;

import com.app.community.domain.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{articleId}/new")
    public ResponseEntity<Void> create(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "articleId") Long articleId,
            @RequestBody CommentRequest.CreateRequest request
    ) {
        commentService.create(memberId, articleId, request.targetId(), request.targetType(), request.content());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "articleId") Long articleId,
            @RequestBody CommentRequest.UpdateRequest request
    ) {
        commentService.update(memberId, articleId, request.content());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Long memberId,
            @PathVariable(name = "articleId") Long articleId
    ) {
        commentService.delete(memberId, articleId);
        return ResponseEntity.ok().build();
    }

}
