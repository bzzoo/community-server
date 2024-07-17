package com.app.community.domain.aritcle;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class Keyword {
    private @Nullable Long id;
    private @NotNull String keywordName;

    @Builder
    public Keyword(
            @Nullable Long id,
            @NotNull String keywordName
    ) {
        this.id = id;
        this.keywordName = keywordName;
    }

    public static Keyword create(String keywordName) {
        return Keyword.builder()
                .keywordName(keywordName)
                .build();
    }
}
