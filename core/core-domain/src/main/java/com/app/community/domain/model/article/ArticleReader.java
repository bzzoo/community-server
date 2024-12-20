package com.app.community.domain.model.article;

import com.app.community.domain.support.error.DomainException;
import com.app.community.domain.support.error.DomainErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleReader {

    private final ArticleRepository articleRepository;

    public Article getById(Long articleId){
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new DomainException(DomainErrorType.DEFAULT_ERROR));
    }

    public Long getWriter(Long articleId){
        return getById(articleId).getWriterId();
    }
}
