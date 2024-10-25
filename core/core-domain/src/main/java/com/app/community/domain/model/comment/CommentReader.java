package com.app.community.domain.model.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentReader {

    private final CommentRepository commentRepository;

    public Comment getById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    public boolean existsByArticleId(Long articleId){return commentRepository.existsByArticleId(articleId);}
}
