package com.app.community.domain.agg.article;

import java.util.Arrays;

public enum ArticleType {
    SHARE, QUESTION;

    public static ArticleType from(String type) {
        return Arrays.stream(ArticleType.values())
                .filter(articleType -> articleType.name().equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }
}
