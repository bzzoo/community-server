package com.app.community.controller.request;

import com.app.community.domain.agg.article.ArticleContent;
import com.app.community.domain.agg.article.ArticleType;
import com.app.community.domain.agg.article.KeywordName;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleRequests {

    public record NewArticleRequest(
            String title,
            String content,
            String articleType,
            List<KeywordRequest> keywords
    ) {
        public ArticleContent toContent() {
            return new ArticleContent(
                    title,
                    content,
                    ArticleType.from(articleType)
            );
        }

        public List<KeywordName> toKeywordNames() {
            return keywords.stream()
                    .map(KeywordRequest::toKeywordName)
                    .collect(Collectors.toList());
        }
    }

    public record UpdateRequest(
            String title,
            String content,
            String articleType,
            List<KeywordRequest> keywords
    ) {
        public ArticleContent toContent() {
            return new ArticleContent(
                    title,
                    content,
                    ArticleType.from(articleType)
            );
        }

        public List<KeywordName> toKeywordNames() {
            return keywords.stream()
                    .map(KeywordRequest::toKeywordName)
                    .collect(Collectors.toList());
        }
    }

    public record KeywordRequest(
            String name
    ) {
        public KeywordName toKeywordName() {
            return new KeywordName(name);
        }
    }
}
