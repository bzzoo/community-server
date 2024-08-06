package com.app.community.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class PointHistory {

    private @Nullable Long id;
    private @NotNull Long memberId;
    private @NotNull Integer value;
    private @NotNull Long targetId;
    private @NotNull PointTransactionType type;

    @AllArgsConstructor
    public enum PointTransactionType {
        SHARE_ARTICLE_CREATION("공유글 게시"),
        SHARE_ARTICLE_DELETION("공유글 삭제"),
        RECEIVED_SHARE_UPVOTE("공유글 추천 받음"),
        QUESTION_COMMENT_CREATION("질문글 답변"),
        QUESTION_REPLY_CREATION("댓글 답변"),
        RECEIVED_QUESTION_COMMENT_UPVOTE("질문글 답변 추천받음");
        private final String description;
    }

    @Builder
    public PointHistory(
            @Nullable Long id,
            @NotNull Long memberId,
            @NotNull Integer value,
            @NotNull Long targetId,
            @NotNull PointTransactionType type
    ) {
        this.id = id;
        this.memberId = memberId;
        this.value = value;
        this.targetId = targetId;
        this.type = type;
    }

    public static PointHistory create(
            Long memberId,
            Integer value,
            Long targetId,
            PointTransactionType type
    ) {
        return PointHistory.builder()
                .memberId(memberId)
                .value(value)
                .targetId(targetId)
                .type(type)
                .build();
    }
}
