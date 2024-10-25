package com.app.community.domain.model.article;

import lombok.Getter;

@Getter
public class Keyword {
    private final Long id;
    private final KeywordName name;

    public Keyword(Long id, KeywordName name) {
        this.id = id;
        this.name = name;
    }
}
