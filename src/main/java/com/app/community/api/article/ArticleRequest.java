package com.app.community.api.article;

import lombok.Getter;
import java.util.List;

@Getter
public class ArticleRequest {

    public record CreateRequest(
            String title,
            String content,
            String articleType,
            List<String> keywordList
    ) {}

    public record UpdateRequest(
            Long articleId,
            String title,
            String content,
            String articleType,
            List<String> keywordList
    ) {}
}
