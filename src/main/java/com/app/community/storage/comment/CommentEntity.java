package com.app.community.storage.comment;

import com.app.community.domain.comment.Comment;
import com.app.community.domain.comment.CommentTarget;
import com.app.community.storage.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
@Entity
public class CommentEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentCommentId;
    private Long articleId;
    private Long writerId;
    private String content;
    private boolean isDelete;

    @Builder
    public CommentEntity(
            Long id,
            Long articleId,
            Long writerId,
            String content,
            Long parentCommentId,
            boolean isDelete
    ) {
        this.id = id;
        this.articleId = articleId;
        this.writerId = writerId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.isDelete = isDelete;
    }

    public static CommentEntity fromDomain(Comment comment) {
        Long articleId = (comment.getCommentTarget() != null &&
                          comment.getCommentTarget().targetType() == CommentTarget.TargetType.SHARE_ARTICLE)
                ? comment.getCommentTarget().targetId()
                : null;

        Long parentCommentId = (comment.getCommentTarget() != null &&
                                comment.getCommentTarget().targetType() == CommentTarget.TargetType.COMMENT)
                ? comment.getCommentTarget().targetId()
                : null;

        return CommentEntity.builder()
                .id(comment.getId())
                .writerId(comment.getWriterId())
                .content(comment.getContent())
                .articleId(articleId)
                .parentCommentId(parentCommentId)
                .isDelete(comment.isDelete())
                .build();
    }

    public Comment toDomain() {
        CommentTarget commentTarget = (articleId != null)
                ? new CommentTarget(articleId, CommentTarget.TargetType.SHARE_ARTICLE)
                : (parentCommentId != null)
                ? new CommentTarget(parentCommentId, CommentTarget.TargetType.COMMENT)
                : null;

        return Comment.builder()
                .id(id)
                .writerId(writerId)
                .content(content)
                .commentTarget(commentTarget)
                .isDelete(isDelete)
                .build();
    }
}
