package com.app.community.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

public class CommentSummary {

    public record CommentInfo(
            Long commentId,
            Long articleId,
            Long parentId,
            String content,
            Boolean is_delete,
            Author author,
            List<CommentInfo> childCommentList,
            LocalDateTime createdAt,
            LocalDateTime updatedAt

    ){
    }

    public record Author(
            Long memberId,
            String nickname,
            String profileImagePath,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
