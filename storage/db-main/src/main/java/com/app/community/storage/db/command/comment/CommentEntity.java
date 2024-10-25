package com.app.community.storage.db.command.comment;

import com.app.community.domain.model.comment.Comment;
import com.app.community.domain.model.comment.CommentStatus;
import com.app.community.domain.model.comment.CommentTarget;
import com.app.community.domain.model.comment.CommentTargetType;
import com.app.community.storage.db.command.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
@Entity
public class CommentEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "writer_id")
    private Long writerId;

    @Lob
    @Column(name = "body")
    private String body;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @Column(name = "upvote_cnt")
    private Integer upvoteCount;

    @Enumerated(EnumType.STRING)
    private CommentTargetType targetType;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;


    @PrePersist
    public void prePersist() {
        this.upvoteCount = 0;
    }

    public static CommentEntity fromDomain(Comment comment) {
        Long parentId = comment.getTarget().type().equals(CommentTargetType.COMMENT)
                ? comment.getTarget().targetId()
                : null;

        return CommentEntity.builder()
                .id(comment.getId())
                .articleId(comment.getArticleId())
                .writerId(comment.getWriterId())
                .body(comment.getBody())
                .parentCommentId(parentId)
                .targetType(comment.getTarget().type())
                .status(comment.getStatus())
                .build();
    }

    public Comment toDomain() {
        CommentTarget commentTarget = new CommentTarget(parentCommentId, targetType);
        return new Comment(id, writerId, articleId, body, commentTarget, status);
    }
}
