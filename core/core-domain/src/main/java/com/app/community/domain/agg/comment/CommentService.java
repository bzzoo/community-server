package com.app.community.domain.agg.comment;

import com.app.community.domain.agg.member.LoginMember;
import com.app.community.domain.agg.point.PointHistoryProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentWriter commentWriter;
    private final CommentReader commentReader;

    public void create(LoginMember member, Long articleId, CommentTarget target, String content) {
        commentWriter.append(member.memberId(), articleId, target,content);
    }

    public void update(LoginMember member, Long commentId, String content) {
        Comment comment = commentReader.getById(commentId);
        commentWriter.update(member, comment, content);
    }

    public void delete(LoginMember member, Long commentId) {
        Comment comment = commentReader.getById(commentId);
        commentWriter.delete(member, comment);
    }
}