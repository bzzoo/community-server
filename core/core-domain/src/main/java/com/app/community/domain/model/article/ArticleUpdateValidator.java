package com.app.community.domain.model.article;

import com.app.community.domain.model.comment.CommentReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleUpdateValidator {

    private final CommentReader commentReader;

    public void validate(Article article) {
        boolean hasComment = commentReader.existsByArticleId(article.getId());
    }
}
