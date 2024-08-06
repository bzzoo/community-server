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
    private CommentTarget.TargetType targetType;
    private boolean isDelete;

    @Builder
    public CommentEntity(
            Long id,
            Long articleId,
            Long writerId,
            String content,
            Long parentCommentId,
            CommentTarget.TargetType targetType,
            boolean isDelete
    ) {
        this.id = id;
        this.articleId = articleId;
        this.writerId = writerId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.targetType = targetType;
        this.isDelete = isDelete;
    }

    public static CommentEntity fromDomain(Comment comment) {
        Long parentCommentId = (comment.getCommentTarget() != null &&
                                comment.getCommentTarget().targetType() == CommentTarget.TargetType.COMMENT)
                ? comment.getCommentTarget().targetId()
                : null;
        return CommentEntity.builder()
                .id(comment.getId())
                .writerId(comment.getWriterId())
                .content(comment.getContent())
                .articleId(comment.getArticleId())
                .parentCommentId(parentCommentId)
                .targetType(comment.getCommentTarget().targetType())
                .isDelete(comment.isDelete())
                .build();
    }

    public Comment toDomain() {
        CommentTarget commentTarget = (articleId.equals(parentCommentId))
                ? new CommentTarget(articleId, targetType)
                : new CommentTarget(parentCommentId, targetType);

        return Comment.builder()
                .id(id)
                .writerId(writerId)
                .articleId(articleId)
                .content(content)
                .commentTarget(commentTarget)
                .isDelete(isDelete)
                .build();
    }
}
