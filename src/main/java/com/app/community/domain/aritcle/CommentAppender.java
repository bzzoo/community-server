package com.app.community.domain.aritcle;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentAppender {

    private final CommentRepository commentRepository;

    public void append(
            @NotNull Long memberId,
            @NotNull Long targetId,
            CommentTarget.@NotNull TargetType targetType,
            @NotNull String content
    ) {

    }
}
