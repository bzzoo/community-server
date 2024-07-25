package com.app.community.domain.comment;

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

        Comment comment = Comment.create(memberId, new CommentTarget(targetId, targetType), content);
        commentRepository.save(comment);
    }
}
