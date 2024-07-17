package com.app.community.domain.aritcle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleReader {

    private final ArticleRepository articleRepository;

    public Article getById(Long articleId){
        return articleRepository.findById(articleId).orElseThrow();
    }

    public Long getWriterId(Long articleId){
        return getById(articleId).getWriterId();
    }
}
