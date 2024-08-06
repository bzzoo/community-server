package com.app.community.domain.aritcle;

import com.app.community.domain.comment.CommentReader;
import com.app.community.support.error.CoreApiException;
import com.app.community.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleUpdateValidate {

    private final CommentReader commentReader;

    public void isUpdatable(Long articleId){
        if(commentReader.existsByArticleId(articleId))
            throw new CoreApiException(ErrorType.ARTICLE_UNMODIFIABLE);
    }
}
