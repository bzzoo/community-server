package com.app.community.domain.agg.article;

import java.util.Set;

public record ArticleKeywordList(
        Set<Long> keywordIds
) {
}
