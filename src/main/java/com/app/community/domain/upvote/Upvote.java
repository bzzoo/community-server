package com.app.community.domain.upvote;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Upvote {

    private @Nullable Long id;
    private @NotNull Long executorId;
    private @NotNull Long targetId;
    private @NotNull TargetType targetType;

    public enum TargetType {
        QUESTION_COMMENT, SHARE_ARTICLE;
    }

    @Builder
    private Upvote(
            @Nullable Long id,
            @NotNull Long executorId,
            @NotNull Long targetId,
            @NotNull TargetType targetType
    ) {
        this.id = id;
        this.executorId = executorId;
        this.targetId = targetId;
        this.targetType = targetType;
    }

    public static Upvote create(
            @NotNull Long executorId,
            @NotNull Long targetId,
            @NotNull TargetType targetType
    ) {
        return Upvote.builder()
                .executorId(executorId)
                .targetId(targetId)
                .targetType(targetType)
                .build();
    }
}
