package com.app.community.domain.comment;

import com.app.community.domain.member.MemberPointManager;
import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentAppender commentAppender;
    private final CommentReader commentReader;
    private final MemberPointManager pointManager;

    @Transactional
    public void create(
            @NotNull Long memberId,
            @NotNull Long targetId,
            @NotNull CommentTarget.TargetType targetType,
            @NotNull String content
    ) {
        pointManager.processQuestionComment(targetType, memberId, targetId);
        commentAppender.append(memberId, targetId, targetType, content);
    }

    @Transactional
    public void update(
            @NotNull Long memberId,
            @NotNull Long commentId,
            @NotNull String content
    ){
        Comment comment = commentReader.getById(commentId);
        commentAppender.update(memberId, comment, content);
    }

    @Transactional
    public void delete(
            @NotNull Long memberId,
            @NotNull Long commentId
    ){
        Comment comment = commentReader.getById(commentId);
        commentAppender.delete(memberId, comment);
    }
}
