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

    public void update(
            @NotNull Long memberId,
            Comment comment,
            String content
    ) {
        comment.update(memberId, content);
        commentRepository.save(comment);
    }

    public void delete(Long memberId, Comment comment) {
        comment.delete(memberId);
        commentRepository.save(comment);
    }
}
