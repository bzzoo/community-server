package com.app.community.domain.agg.article;

import com.app.community.domain.support.error.CoreApiException;
import com.app.community.domain.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleReader {

    private final ArticleRepository articleRepository;

    public Article getById(Long articleId){
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new CoreApiException(ErrorType.DEFAULT_ERROR));
    }
}
