package com.app.community.domain.aritcle;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Comment {
    private @Nullable Long id;
    private @NotNull Long writerId;
    private @NotNull String content;
    private CommentTarget commentTarget;
    private boolean isDelete;


    @Builder
    private Comment(
            @Nullable Long id,
            @NotNull Long writerId,
            @NotNull String content,
            CommentTarget commentTarget,
            boolean isDelete
    ) {
        this.id = id;
        this.writerId = writerId;
        this.content = content;
        this.commentTarget = commentTarget;
        this.isDelete = isDelete;
    }

    public static Comment create(
            Long memberId,
            CommentTarget commentTarget,
            String content
    ) {
        return null;
    }
}
