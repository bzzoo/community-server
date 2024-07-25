package com.app.community.domain.comment;

import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentAppender commentAppender;

    @Transactional
    public void create(
            @NotNull Long memberId,
            @NotNull Long targetId,
            @NotNull CommentTarget.TargetType targetType,
            @NotNull String content
    ) {
        commentAppender.append(memberId, targetId, targetType, content);
    }
}
