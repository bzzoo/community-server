package com.app.community.domain.aritcle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentReader {

    private final CommentRepository commentRepository;

    public Comment getById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }
}
