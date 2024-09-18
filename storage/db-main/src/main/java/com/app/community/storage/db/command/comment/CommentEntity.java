package com.app.community.storage.db.command.comment;

import com.app.community.domain.agg.comment.Comment;
import com.app.community.domain.agg.comment.CommentStatus;
import com.app.community.domain.agg.comment.CommentTarget;
import com.app.community.domain.agg.comment.CommentTargetType;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
@Entity
public class CommentEntity extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long articleId;
    private Long writerId;
    @Lob private String content;
    private Long parentCommentId;

    @Enumerated(EnumType.STRING)
    private CommentTargetType targetType;
    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    @Builder
    public CommentEntity(
            Long id,
            Long articleId,
            Long writerId,
            String content,
            Long parentCommentId,
            CommentTargetType targetType,
            CommentStatus status
    ) {
        this.id = id;
        this.articleId = articleId;
        this.writerId = writerId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.targetType = targetType;
        this.status = status;
    }

    public Comment toDomain() {
        CommentTarget commentTarget = (articleId.equals(parentCommentId))
                ? new CommentTarget(articleId, targetType)
                : new CommentTarget(parentCommentId, targetType);

        return new Comment(id, writerId, articleId, content ,commentTarget, status);
    }
}
