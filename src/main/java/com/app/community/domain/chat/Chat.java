package com.app.community.domain.chat;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Getter
public class Chat {

    private @Nullable Long id;
    private @NotNull Long respondentId;
    private @NotNull Long requesterId;
    private @NotNull Boolean isEnd;
    private @NotNull LocalDateTime endDate;

    @Builder
    private Chat(
            @Nullable Long id,
            @NotNull Long respondentId,
            @NotNull Long requesterId,
            @NotNull Boolean isEnd,
            @NotNull LocalDateTime endDate
    ) {
        this.id = id;
        this.respondentId = respondentId;
        this.requesterId = requesterId;
        this.isEnd = isEnd;
        this.endDate = endDate;
    }

    public static Chat create(
            @NotNull Long respondentId,
            @NotNull Long requesterId
    ) {
        return Chat.builder()
                .requesterId(requesterId)
                .respondentId(respondentId)
                .isEnd(false)
                .endDate(LocalDateTime.now().plusWeeks(1))
                .build();
    }
}
