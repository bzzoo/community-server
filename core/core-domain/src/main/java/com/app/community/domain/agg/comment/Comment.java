package com.app.community.domain.agg.comment;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Comment {
    private final Long id;
    private final Long writerId;
    private final Long articleId;
    private final CommentTarget target;
    private String content;
    private CommentStatus status;

    public Comment(
            Long id,
            Long writerId,
            Long articleId,
            String content,
            CommentTarget target,
            CommentStatus status
    ) {
        this.id = id;
        this.writerId = writerId;
        this.articleId = articleId;
        this.target = target;
        this.content = content;
        this.status = status;
    }

    public static Comment create(
            Long writerId,
            Long articleId,
            String content,
            CommentTarget target
    ) {
        return new Comment(null, writerId, articleId, content, target, CommentStatus.STEADY);
    }

    public void update(Long memberId, String content){
        validateWriter(memberId);
        this.content = content;
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
