package com.app.community.api.controller.request;

import com.app.community.domain.model.article.ArticleContent;
import com.app.community.domain.model.article.ArticleType;
import com.app.community.domain.model.article.KeywordName;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleRequests {

    public record NewArticleRequest(
            String title,
            String body,
            String type,
            List<String> keywords
    ) {
        public ArticleContent toContent() {
            return new ArticleContent(title, body);
        }

        public List<KeywordName> toKeywordNames() {
            if(keywords == null) return List.of();
            return keywords.stream()
                    .map(KeywordName::new)
                    .collect(Collectors.toList());
        }

        public ArticleType toType() {
            return ArticleType.from(type);
        }
    }

    public record UpdateRequest(
            String title,
            String body,
            List<String> keywords
    ) {
        public ArticleContent toContent() {
            return new ArticleContent(title, body);
        }

        public List<KeywordName> toKeywordNames() {
            return keywords.stream()
                    .map(KeywordName::new)
                    .collect(Collectors.toList());
        }
    }
}
