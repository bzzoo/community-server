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
            List<String> keywords
    ) {
        public ArticleContent toContent() {
            return new ArticleContent(title, content);
        }

        public List<KeywordName> toKeywordNames() {
            return keywords.stream()
                    .map(KeywordName::new)
                    .collect(Collectors.toList());
        }

        public ArticleType toType() {
            return ArticleType.from(articleType);
        }
    }

    public record UpdateRequest(
            String title,
            String content,
            List<String> keywords
    ) {
        public ArticleContent toContent() {
            return new ArticleContent(title, content);
        }

        public List<KeywordName> toKeywordNames() {
            return keywords.stream()
                    .map(KeywordName::new)
                    .collect(Collectors.toList());
        }
    }
}
