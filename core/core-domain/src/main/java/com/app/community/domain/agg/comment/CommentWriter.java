package com.app.community.domain.agg.comment;

import com.app.community.domain.agg.member.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentWriter {

    private final CommentRepository commentRepository;

    public void append(Long memberId, Long articleId, CommentTarget target, String content) {
        Comment newComment = Comment.create(memberId, articleId, content, target);
        commentRepository.save(newComment);
    }

    public void update(LoginMember member, Comment comment, String content) {
        comment.update(member.memberId(), content);
        commentRepository.save(comment);
    }

    public void delete(LoginMember member, Comment comment) {
        comment.withdrawal(member.memberId());
        commentRepository.save(comment);
    }
}
