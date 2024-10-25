package com.app.community.domain.model.article;

import java.util.Set;

public record ArticleKeywordList(
        Set<Long> keywordIds
) {
}
