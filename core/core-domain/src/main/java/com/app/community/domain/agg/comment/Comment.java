package com.app.community.domain.agg.comment;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Comment {
    private final Long id;
    private final Long writerId;
    private final Long articleId;
    private String body;
    private final CommentTarget target;
    private CommentStatus status;

    public Comment(
            Long id,
            Long writerId,
            Long articleId,
            String body,
            CommentTarget target,
            CommentStatus status
    ) {
        this.id = id;
        this.writerId = writerId;
        this.articleId = articleId;
        this.target = target;
        this.body = body;
        this.status = status;
    }

    public static Comment create(
            Long writerId,
            Long articleId,
            String body,
            CommentTarget target
    ) {
        return new Comment(null, writerId, articleId, body, target, CommentStatus.STEADY);
    }

    public void update(Long memberId, String body){
        validateWriter(memberId);
        this.body = body;
    }

    public void withdrawal(Long memberId){
        validateWriter(memberId);
        this.status = CommentStatus.DELETED;
    }

    public void validateWriter(Long memberId){
        if(!Objects.equals(this.writerId, memberId)){
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
