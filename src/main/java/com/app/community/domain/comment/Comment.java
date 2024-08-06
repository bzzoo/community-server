package com.app.community.domain.comment;

import com.app.community.support.error.CoreApiException;
import com.app.community.support.error.ErrorType;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Comment {
    private @Nullable Long id;
    private @NotNull Long writerId;
    private @NotNull Long articleId;
    private @NotNull String content;
    private CommentTarget commentTarget;
    private boolean isDelete;


    @Builder
    private Comment(
            @Nullable Long id,
            @NotNull Long writerId,
            @NotNull Long articleId,
            @NotNull String content,
            CommentTarget commentTarget,
            boolean isDelete
    ) {
        this.id = id;
        this.writerId = writerId;
        this.articleId = articleId;
        this.content = content;
        this.commentTarget = commentTarget;
        this.isDelete = isDelete;
    }

    public static Comment create(
            Long memberId,
            Long articleId,
            CommentTarget commentTarget,
            String content
    ) {
        return Comment.builder()
                .writerId(memberId)
                .articleId(articleId)
                .commentTarget(commentTarget)
                .content(content)
                .isDelete(false)
                .build();
    }


    public void validateOwner(Long memberId) {
        if (!this.writerId.equals(memberId)) throw new CoreApiException(ErrorType.DEFAULT_ERROR);
    }

    public void update(Long memberId, String content) {
        validateOwner(memberId);
        this.content = content;
    }

    public void delete(Long memberId) {
        validateOwner(memberId);
        this.isDelete = true;
    }
}
